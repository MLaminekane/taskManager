package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    
    fun getActiveTasks(): Flow<List<Task>>
    
    fun getLocationBasedTasks(): Flow<List<Task>>
    
    fun getTasksInTimeRange(start: LocalDateTime, end: LocalDateTime): Flow<List<Task>>
    
    suspend fun getTaskById(taskId: Long): Task?
    
    suspend fun insertTask(task: Task): Long
    
    suspend fun updateTask(task: Task)
    
    suspend fun deleteTask(task: Task)
    
    suspend fun deleteCompletedTasks()

    suspend fun getTasksByDateRange(startDateTime: LocalDateTime, endDateTime: LocalDateTime): List<Task>
} 