package com.mlk.taskmanager.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val defaultReminderTime: LocalTime = LocalTime.of(9, 0)
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

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
                otherFlow
            ) { appearance, notifications, location, other ->
                SettingsUiState(
                    isDarkMode = appearance.first,
                    useDynamicColors = appearance.second,
                    areNotificationsEnabled = notifications.first,
                    isSoundEnabled = notifications.second,
                    isVibrationEnabled = notifications.third,
                    isLocationEnabled = location.first,
                    defaultLocationRadius = location.second,
                    categories = other.first,
                    defaultReminderTime = other.second
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
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
}