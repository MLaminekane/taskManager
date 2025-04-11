package com.mlk.taskmanager.di

import android.content.Context
import androidx.room.Room
import com.mlk.taskmanager.data.TaskManagerDatabase
import com.mlk.taskmanager.data.dao.RoutineDao
import com.mlk.taskmanager.data.dao.TaskDao
import com.mlk.taskmanager.data.dao.ProjectDao
import com.mlk.taskmanager.data.dao.UserDao
import com.mlk.taskmanager.data.local.TaskDatabase
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
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "tasks_database"
        )
        .addMigrations(
            TaskDatabase.MIGRATION_1_2,
            TaskDatabase.MIGRATION_2_3,
            TaskDatabase.MIGRATION_3_4,
            TaskDatabase.MIGRATION_4_5
        )
        .build()
    }
    
    @Provides
    @Singleton
    fun provideTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao
    }
    
    @Provides
    @Singleton
    fun provideRoutineDao(database: TaskDatabase): RoutineDao {
        return database.routineDao
    }
    
    @Provides
    @Singleton
    fun provideProjectDao(database: TaskDatabase): ProjectDao {
        return database.projectDao
    }
    
    @Provides
    @Singleton
    fun provideUserDao(database: TaskDatabase): UserDao {
        return database.userDao
    }
}