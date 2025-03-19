package com.mlk.taskmanager.di

import android.content.Context
import com.mlk.taskmanager.data.local.TaskDatabase
import com.mlk.taskmanager.data.repository.ProjectRepository
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
} 