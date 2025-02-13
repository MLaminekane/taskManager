package com.mlk.taskmanager.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsRepository {

    private object PreferencesKeys {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val DYNAMIC_COLORS = booleanPreferencesKey("dynamic_colors")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        val VIBRATION_ENABLED = booleanPreferencesKey("vibration_enabled")
        val LOCATION_ENABLED = booleanPreferencesKey("location_enabled")
        val DEFAULT_LOCATION_RADIUS = floatPreferencesKey("default_location_radius")
        val CATEGORIES = stringPreferencesKey("categories")
        val DEFAULT_REMINDER_TIME = stringPreferencesKey("default_reminder_time")
    }

    override suspend fun isDarkMode(): Flow<Boolean> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.DARK_MODE] ?: false
        }
    }

    override suspend fun setDarkMode(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_MODE] = enabled
        }
    }

    override suspend fun useDynamicColors(): Flow<Boolean> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.DYNAMIC_COLORS] ?: true
        }
    }

    override suspend fun setDynamicColors(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.DYNAMIC_COLORS] = enabled
        }
    }

    override suspend fun areNotificationsEnabled(): Flow<Boolean> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.NOTIFICATIONS_ENABLED] ?: true
        }
    }

    override suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.NOTIFICATIONS_ENABLED] = enabled
        }
    }

    override suspend fun isSoundEnabled(): Flow<Boolean> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.SOUND_ENABLED] ?: true
        }
    }

    override suspend fun setSoundEnabled(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.SOUND_ENABLED] = enabled
        }
    }

    override suspend fun isVibrationEnabled(): Flow<Boolean> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.VIBRATION_ENABLED] ?: true
        }
    }

    override suspend fun setVibrationEnabled(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.VIBRATION_ENABLED] = enabled
        }
    }

    override suspend fun isLocationEnabled(): Flow<Boolean> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.LOCATION_ENABLED] ?: false
        }
    }

    override suspend fun setLocationEnabled(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.LOCATION_ENABLED] = enabled
        }
    }

    override suspend fun getDefaultLocationRadius(): Flow<Float> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.DEFAULT_LOCATION_RADIUS] ?: 100f
        }
    }

    override suspend fun setDefaultLocationRadius(radius: Float) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.DEFAULT_LOCATION_RADIUS] = radius
        }
    }

    override suspend fun getCategories(): Flow<List<String>> {
        return context.settingsDataStore.data.map { preferences ->
            preferences[PreferencesKeys.CATEGORIES]?.split(",") ?: listOf("Work", "Personal", "Shopping", "Health")
        }
    }

    override suspend fun setCategories(categories: List<String>) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.CATEGORIES] = categories.joinToString(",")
        }
    }

    override suspend fun getDefaultReminderTime(): Flow<LocalTime> {
        return context.settingsDataStore.data.map { preferences ->
            LocalTime.parse(preferences[PreferencesKeys.DEFAULT_REMINDER_TIME] ?: "09:00")
        }
    }

    override suspend fun setDefaultReminderTime(time: LocalTime) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.DEFAULT_REMINDER_TIME] = time.toString()
        }
    }
}