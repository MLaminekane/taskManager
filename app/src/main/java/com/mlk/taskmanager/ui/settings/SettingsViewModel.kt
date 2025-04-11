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
import com.google.android.gms.common.api.CommonStatusCodes
import com.mlk.taskmanager.data.model.User
import com.mlk.taskmanager.data.repository.SettingsRepository
import com.mlk.taskmanager.data.repository.UserRepository
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
    val syncError: String? = null,
    val lastSyncMessage: String? = null,
    val isUserLoggedIn: Boolean = false,
    val currentUser: User? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val userRepository: UserRepository,
    private val calendarSyncService: CalendarSyncService,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()
    
    private var googleSignInClient: GoogleSignInClient? = null

    init {
        loadSettings()
    }
    
    private fun loadSettings() {
        viewModelScope.launch {
            // Load dark mode setting
            val isDarkMode = settingsRepository.isDarkMode().first()
            // Load dynamic colors setting
            val useDynamicColors = settingsRepository.useDynamicColors().first()
            
            // Load notification settings
            val areNotificationsEnabled = settingsRepository.areNotificationsEnabled().first()
            val isSoundEnabled = settingsRepository.isSoundEnabled().first()
            val isVibrationEnabled = settingsRepository.isVibrationEnabled().first()
            
            // Load location settings
            val isLocationEnabled = settingsRepository.isLocationEnabled().first()
            val locationRadius = settingsRepository.getDefaultLocationRadius().first()
            
            // Load calendar settings
            val isCalendarSyncEnabled = settingsRepository.isCalendarSyncEnabled().first()
            val isGoogleSignedIn = checkGoogleSignIn()
            
            // Load categories and reminder time
            val categories = settingsRepository.getCategories().first()
            val reminderTime = settingsRepository.getDefaultReminderTime().first()
            
            // Check user login status
            val isUserLoggedIn = userRepository.isUserLoggedIn()
            val currentUserId = userRepository.getCurrentUserId()
            var currentUser: User? = null
            
            if (isUserLoggedIn && currentUserId != null) {
                currentUser = userRepository.getUserById(currentUserId).first()
            }
            
            // Update UI state with all settings
            _uiState.value = SettingsUiState(
                isDarkMode = isDarkMode,
                useDynamicColors = useDynamicColors,
                areNotificationsEnabled = areNotificationsEnabled,
                isSoundEnabled = isSoundEnabled,
                isVibrationEnabled = isVibrationEnabled,
                isLocationEnabled = isLocationEnabled,
                defaultLocationRadius = locationRadius,
                isCalendarSyncEnabled = isCalendarSyncEnabled,
                isGoogleSignedIn = isGoogleSignedIn,
                googleAccountEmail = getGoogleAccountEmail(),
                categories = categories,
                defaultReminderTime = reminderTime,
                isUserLoggedIn = isUserLoggedIn,
                currentUser = currentUser
            )
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
    
    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            _uiState.value = _uiState.value.copy(
                isUserLoggedIn = false,
                currentUser = null
            )
        }
    }
    
    // Google Sign-In related functions
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
                android.util.Log.e("SettingsViewModel", "ApiException during sign-in: ${e.statusCode}, message: ${e.message}", e)
                
                when (e.statusCode) {
                    // Connexion en cours (12501)
                    12501 -> {
                        _uiState.value = _uiState.value.copy(
                            isSyncing = false,
                            syncError = "Une connexion est déjà en cours... Veuillez patienter."
                        )
                    }
                    // Connexion annulée par l'utilisateur (12500)
                    12500 -> {
                        _uiState.value = _uiState.value.copy(
                            isSyncing = false,
                            syncError = null  // L'utilisateur a annulé la connexion
                        )
                    }
                    // Autres erreurs
                    else -> {
                        _uiState.value = _uiState.value.copy(
                            isSyncing = false,
                            syncError = "Erreur de connexion: ${e.statusCode}"
                        )
                    }
                }
            } catch (e: Exception) {
                android.util.Log.e("SettingsViewModel", "Unexpected error during sign-in", e)
                _uiState.value = _uiState.value.copy(
                    isSyncing = false,
                    syncError = "Erreur inattendue: ${e.message}"
                )
            }
        }
    }

    fun signInToGoogle(): GoogleSignInClient {
        // Vérifier si une connexion est déjà en cours
        if (_uiState.value.isSyncing) {
            // Une connexion est déjà en cours, utiliser le client existant
            return googleSignInClient ?: GoogleSignIn.getClient(context, calendarSyncService.getGoogleSignInOptions())
        }
        
        // Sinon, créer un nouveau client
        googleSignInClient = GoogleSignIn.getClient(context, calendarSyncService.getGoogleSignInOptions())
        return googleSignInClient!!
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
    
    // Synchroniser toutes les routines
    fun syncAllRoutines() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isSyncing = true, syncError = null)
                
                val result = calendarSyncService.syncAllRoutines()
                if (result.isSuccess) {
                    val count = result.getOrDefault(0)
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        lastSyncMessage = "Synchronisation réussie: $count routines"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        syncError = result.exceptionOrNull()?.message ?: "Erreur de synchronisation"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSyncing = false,
                    syncError = e.message ?: "Erreur inattendue"
                )
            }
        }
    }
    
    // Synchroniser toutes les tâches
    fun syncAllTasks() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isSyncing = true, syncError = null)
                
                val result = calendarSyncService.syncAllTasks()
                if (result.isSuccess) {
                    val count = result.getOrDefault(0)
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        lastSyncMessage = "Synchronisation réussie: $count tâches"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        syncError = result.exceptionOrNull()?.message ?: "Erreur de synchronisation des tâches"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSyncing = false,
                    syncError = e.message ?: "Erreur inattendue"
                )
            }
        }
    }
    
    // Synchroniser tout (routines et tâches)
    fun syncAll() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isSyncing = true, syncError = null)
                
                val routinesResult = calendarSyncService.syncAllRoutines()
                val tasksResult = calendarSyncService.syncAllTasks()
                
                val routinesCount = if (routinesResult.isSuccess) routinesResult.getOrDefault(0) else 0
                val tasksCount = if (tasksResult.isSuccess) tasksResult.getOrDefault(0) else 0
                
                if (routinesResult.isSuccess && tasksResult.isSuccess) {
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        lastSyncMessage = "Synchronisation réussie: $routinesCount routines, $tasksCount tâches"
                    )
                } else {
                    val errorMessage = when {
                        !routinesResult.isSuccess && !tasksResult.isSuccess -> "Erreur de synchronisation des routines et des tâches"
                        !routinesResult.isSuccess -> "Erreur de synchronisation des routines"
                        else -> "Erreur de synchronisation des tâches"
                    }
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        syncError = errorMessage
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSyncing = false,
                    syncError = e.message ?: "Erreur inattendue"
                )
            }
        }
    }
}