package com.mlk.taskmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.dao.TaskDao
import com.mlk.taskmanager.data.dao.RoutineDao
import com.mlk.taskmanager.data.dao.ProjectDao
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.model.Project
import com.mlk.taskmanager.data.util.Converters

@Database(
    entities = [Task::class, Routine::class, Project::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val routineDao: RoutineDao
    abstract val projectDao: ProjectDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tasks ADD COLUMN category TEXT")
            }
        }
        
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the routines table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS routines (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        title TEXT NOT NULL,
                        description TEXT NOT NULL,
                        time TEXT NOT NULL,
                        repeatDays TEXT NOT NULL,
                        isEnabled INTEGER NOT NULL DEFAULT 1,
                        latitude REAL,
                        longitude REAL,
                        locationRadius REAL,
                        categoryId INTEGER
                    )
                """)
                
                // Add projectId column to tasks table
                database.execSQL("ALTER TABLE tasks ADD COLUMN projectId INTEGER")
            }
        }
        
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the projects table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS projects (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        description TEXT NOT NULL,
                        icon TEXT NOT NULL,
                        color INTEGER NOT NULL,
                        taskCount INTEGER NOT NULL DEFAULT 0
                    )
                """)
                
                // Add default projects
                database.execSQL("""
                    INSERT INTO projects (name, description, icon, color, taskCount)
                    VALUES ('Mobile App', 'Application mobile Android', 'kotlin', ${0xFF613BE7}, 0)
                """)
                
                database.execSQL("""
                    INSERT INTO projects (name, description, icon, color, taskCount)
                    VALUES ('Web App', 'Application web React', 'typescript', ${0xFF4CAF50}, 0)
                """)
                
                // Update task count for existing projects
                database.execSQL("""
                    UPDATE projects SET taskCount = (
                        SELECT COUNT(*) FROM tasks WHERE tasks.projectId = projects.id
                    )
                """)
            }
        }
    }
} 