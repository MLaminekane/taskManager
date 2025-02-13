package com.mlk.taskmanager.di

import android.content.Context
import androidx.room.Room
import com.mlk.taskmanager.data.TaskManagerDatabase
import com.mlk.taskmanager.data.dao.RoutineDao
import com.mlk.taskmanager.data.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TaskManagerDatabase {
        return Room.databaseBuilder(
            context,
            TaskManagerDatabase::class.java,
            "task_manager_db"
        ).build()
    }
    
    @Provides
    fun provideTaskDao(database: TaskManagerDatabase): TaskDao {
        return database.taskDao()
    }
    
    @Provides
    fun provideRoutineDao(database: TaskManagerDatabase): RoutineDao {
        return database.routineDao()
    }
} 