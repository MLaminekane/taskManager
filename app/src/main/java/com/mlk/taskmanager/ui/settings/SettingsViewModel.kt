package com.mlk.taskmanager.ui.settings

import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.mlk.taskmanager.data.repository.SettingsRepository
import com.mlk.taskmanager.service.CalendarSyncService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

data class SettingsUiState(
    val isDarkMode: Boolean = false,
    val useDynamicColors: Boolean = true,
    val areNotificationsEnabled: Boolean = true,
    val isSoundEnabled: Boolean = true,
    val isVibrationEnabled: Boolean = true,
    val isLocationEnabled: Boolean = false,
    val defaultLocationRadius: Float = 100f,
    val categories: List<String> = listOf("Work", "Personal", "Shopping", "Health", "Education"),
    val defaultReminderTime: LocalTime = LocalTime.of(9, 0),
    val isCalendarSyncEnabled: Boolean = false,
    val isGoogleSignedIn: Boolean = false,
    val googleAccountEmail: String? = null,
    val isSyncing: Boolean = false,
    val syncError: String? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val calendarSyncService: CalendarSyncService,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()
    
    private var googleSignInClient: GoogleSignInClient? = null

    init {
        viewModelScope.launch {
            // Combine appearance settings
            val appearanceFlow = combine(
                settingsRepository.isDarkMode(),
                settingsRepository.useDynamicColors()
            ) { darkMode, dynamicColors -> darkMode to dynamicColors }

            // Combine notification settings
            val notificationFlow = combine(
                settingsRepository.areNotificationsEnabled(),
                settingsRepository.isSoundEnabled(),
                settingsRepository.isVibrationEnabled()
            ) { notifications, sound, vibration -> Triple(notifications, sound, vibration) }

            // Combine location settings
            val locationFlow = combine(
                settingsRepository.isLocationEnabled(),
                settingsRepository.getDefaultLocationRadius()
            ) { location, radius -> location to radius }

            // Combine calendar settings
            val calendarFlow = flowOf(
                settingsRepository.isCalendarSyncEnabled().first() to checkGoogleSignIn()
            )

            // Combine remaining settings
            val otherFlow = combine(
                settingsRepository.getCategories(),
                settingsRepository.getDefaultReminderTime()
            ) { categories, reminderTime -> categories to reminderTime }

            // Combine all flows
            combine(
                appearanceFlow,
                notificationFlow,
                locationFlow,
                calendarFlow,
                otherFlow
            ) { appearance, notifications, location, calendar, other ->
                SettingsUiState(
                    isDarkMode = appearance.first,
                    useDynamicColors = appearance.second,
                    areNotificationsEnabled = notifications.first,
                    isSoundEnabled = notifications.second,
                    isVibrationEnabled = notifications.third,
                    isLocationEnabled = location.first,
                    defaultLocationRadius = location.second,
                    isCalendarSyncEnabled = calendar.first,
                    isGoogleSignedIn = calendar.second,
                    googleAccountEmail = getGoogleAccountEmail(),
                    categories = other.first,
                    defaultReminderTime = other.second
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }
    
    private fun getGoogleAccountEmail(): String? {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return account?.email
    }
    
    private fun checkGoogleSignIn(): Boolean {
        return calendarSyncService.isSignedIn()
    }

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setDarkMode(enabled)
        }
    }

    fun setDynamicColors(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setDynamicColors(enabled)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setNotificationsEnabled(enabled)
        }
    }

    fun setSoundEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setSoundEnabled(enabled)
        }
    }

    fun setVibrationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setVibrationEnabled(enabled)
        }
    }

    fun setLocationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setLocationEnabled(enabled)
        }
    }

    fun setDefaultLocationRadius(radius: Float) {
        viewModelScope.launch {
            settingsRepository.setDefaultLocationRadius(radius)
        }
    }
    
    fun setCalendarSyncEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setCalendarSyncEnabled(enabled)
            
            // Si on désactive la synchro, mettre à jour l'état immédiatement
            if (!enabled) {
                _uiState.value = _uiState.value.copy(isCalendarSyncEnabled = false)
            }
        }
    }

    fun addCategory(category: String) {
        viewModelScope.launch {
            settingsRepository.setCategories(uiState.value.categories + category)
        }
    }

    fun removeCategory(category: String) {
        viewModelScope.launch {
            settingsRepository.setCategories(uiState.value.categories - category)
        }
    }

    fun setDefaultReminderTime(time: LocalTime) {
        viewModelScope.launch {
            settingsRepository.setDefaultReminderTime(time)
        }
    }

    fun toggleDarkMode() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isDarkMode = !_uiState.value.isDarkMode
            )
        }
    }

    fun toggleNotifications() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                areNotificationsEnabled = !_uiState.value.areNotificationsEnabled
            )
        }
    }
    
    // Google Sign-In
    fun signInToGoogle(): GoogleSignInClient {
        googleSignInClient = GoogleSignIn.getClient(context, calendarSyncService.getGoogleSignInOptions())
        return googleSignInClient!!
    }
    
    fun handleSignInResult(result: ActivityResult) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isSyncing = true, syncError = null)
                
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account = task.getResult(ApiException::class.java)
                
                // Initialiser le service Calendar avec le compte
                calendarSyncService.initializeCalendarService(account)
                
                // Activer la synchronisation dans les paramètres
                settingsRepository.setCalendarSyncEnabled(true)
                
                // Mettre à jour l'état
                _uiState.value = _uiState.value.copy(
                    isGoogleSignedIn = true,
                    isCalendarSyncEnabled = true,
                    googleAccountEmail = account.email,
                    isSyncing = false
                )
                
                // Synchroniser toutes les routines
                syncAllRoutines()
            } catch (e: ApiException) {
                _uiState.value = _uiState.value.copy(
                    isSyncing = false,
                    syncError = "Erreur de connexion: ${e.statusCode}"
                )
            }
        }
    }
    
    fun signOutFromGoogle() {
        viewModelScope.launch {
            try {
                googleSignInClient?.signOut()?.addOnCompleteListener {
                    viewModelScope.launch {
                        // Désactiver la synchronisation dans les paramètres
                        settingsRepository.setCalendarSyncEnabled(false)
                        
                        // Mettre à jour l'état
                        _uiState.value = _uiState.value.copy(
                            isGoogleSignedIn = false,
                            isCalendarSyncEnabled = false,
                            googleAccountEmail = null
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    syncError = "Erreur de déconnexion: ${e.message}"
                )
            }
        }
    }
    
    fun syncAllRoutines() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isSyncing = true, syncError = null)
                
                val result = calendarSyncService.syncAllRoutines()
                
                result.onSuccess { count ->
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        syncError = null
                    )
                }.onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        syncError = "Erreur de synchronisation: ${error.message}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSyncing = false,
                    syncError = "Erreur de synchronisation: ${e.message}"
                )
            }
        }
    }
}