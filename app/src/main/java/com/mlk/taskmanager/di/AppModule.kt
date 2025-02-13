package com.mlk.taskmanager.di

import android.content.Context
import androidx.room.Room
import com.mlk.taskmanager.data.local.TaskDatabase
import com.mlk.taskmanager.data.repository.SettingsRepository
import com.mlk.taskmanager.data.repository.SettingsRepositoryImpl
import com.mlk.taskmanager.data.repository.TaskRepository
import com.mlk.taskmanager.data.repository.TaskRepositoryImpl
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
    fun provideTaskDatabase(
        @ApplicationContext context: Context
    ): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "tasks.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        database: TaskDatabase
    ): TaskRepository {
        return TaskRepositoryImpl(database.taskDao)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        @ApplicationContext context: Context
    ): SettingsRepository {
        return SettingsRepositoryImpl(context)
    }
} 