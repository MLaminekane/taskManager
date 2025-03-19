package com.mlk.taskmanager.data.repository

import android.content.Context
import com.mlk.taskmanager.R
import com.mlk.taskmanager.data.api.WeatherApiService
import com.mlk.taskmanager.data.model.WeatherResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    @ApplicationContext private val context: Context
) {
    // Récupérer la clé API depuis les ressources
    private val apiKey by lazy { context.getString(R.string.openweather_api_key) }
    
    fun getCurrentWeather(latitude: Double, longitude: Double): Flow<Result<WeatherResponse>> = flow {
        try {
            val response = weatherApiService.getCurrentWeather(
                latitude = latitude,
                longitude = longitude,
                units = "metric", // Pour avoir la température en Celsius
                apiKey = apiKey
            )
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
} 