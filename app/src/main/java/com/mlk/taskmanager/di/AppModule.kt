package com.mlk.taskmanager.di

import android.content.Context
import com.mlk.taskmanager.data.api.WeatherApiService
import com.mlk.taskmanager.data.local.TaskDatabase
import com.mlk.taskmanager.data.repository.*
import com.mlk.taskmanager.service.LocationReminderService
import com.mlk.taskmanager.service.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskRepository(
        database: TaskDatabase,
        projectRepository: ProjectRepository
    ): TaskRepository {
        return TaskRepositoryImpl(database.taskDao, projectRepository)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        @ApplicationContext context: Context
    ): SettingsRepository {
        return SettingsRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context,
        locationReminderService: LocationReminderService
    ): NotificationManager {
        return NotificationManager(context, locationReminderService)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApiService: WeatherApiService,
        @ApplicationContext context: Context
    ): WeatherRepository {
        return WeatherRepository(weatherApiService, context)
    }
} 