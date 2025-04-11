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
        // Keeping this function as a stub to fix compilation errors
        // Original implementation was removed in the edit
    }
    
    fun signInToGoogle(): GoogleSignInClient {
        // Stub implementation to fix compilation errors
        googleSignInClient = GoogleSignIn.getClient(context, calendarSyncService.getGoogleSignInOptions())
        return googleSignInClient!!
    }
    
    fun signOutFromGoogle() {
        // Stub implementation to fix compilation errors
    }
    
    fun syncAllRoutines() {
        // Stub implementation to fix compilation errors
    }
}