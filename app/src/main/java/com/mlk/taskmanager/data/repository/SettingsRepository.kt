package com.mlk.taskmanager.data.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface SettingsRepository {
    suspend fun isDarkMode(): Flow<Boolean>
    suspend fun setDarkMode(enabled: Boolean)
    suspend fun useDynamicColors(): Flow<Boolean>
    suspend fun setDynamicColors(enabled: Boolean)
    suspend fun areNotificationsEnabled(): Flow<Boolean>
    suspend fun setNotificationsEnabled(enabled: Boolean)
    suspend fun isSoundEnabled(): Flow<Boolean>
    suspend fun setSoundEnabled(enabled: Boolean)
    suspend fun isVibrationEnabled(): Flow<Boolean>
    suspend fun setVibrationEnabled(enabled: Boolean)
    suspend fun isLocationEnabled(): Flow<Boolean>
    suspend fun setLocationEnabled(enabled: Boolean)
    suspend fun getDefaultLocationRadius(): Flow<Float>
    suspend fun setDefaultLocationRadius(radius: Float)
    suspend fun getCategories(): Flow<List<String>>
    suspend fun setCategories(categories: List<String>)
    suspend fun getDefaultReminderTime(): Flow<LocalTime>
    suspend fun setDefaultReminderTime(time: LocalTime)
}