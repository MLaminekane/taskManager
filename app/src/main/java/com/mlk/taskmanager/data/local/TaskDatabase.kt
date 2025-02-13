package com.mlk.taskmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.dao.TaskDao
import com.mlk.taskmanager.data.util.Converters

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
} 