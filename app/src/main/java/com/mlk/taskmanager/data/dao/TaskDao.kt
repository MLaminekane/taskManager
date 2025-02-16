package com.mlk.taskmanager.data.dao

import androidx.room.*
import com.mlk.taskmanager.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY dueDateTime ASC")
    fun getAllTasks(): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY dueDateTime ASC")
    fun getActiveTasks(): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): Task?
    
    @Query("""
        SELECT * FROM tasks 
        WHERE isCompleted = 0 
        AND latitude IS NOT NULL 
        AND longitude IS NOT NULL
        ORDER BY dueDateTime ASC
    """)
    fun getLocationBasedTasks(): Flow<List<Task>>
    
    @Query("""
        SELECT * FROM tasks 
        WHERE isCompleted = 0 
        AND dueDateTime BETWEEN :start AND :end
        ORDER BY dueDateTime ASC
    """)
    fun getTasksInTimeRange(start: LocalDateTime, end: LocalDateTime): Flow<List<Task>>

    @Query("""
        SELECT * FROM tasks 
        WHERE dueDateTime BETWEEN :startDateTime AND :endDateTime 
        ORDER BY dueDateTime ASC
    """)
    suspend fun getTasksByDateRange(startDateTime: LocalDateTime, endDateTime: LocalDateTime): List<Task>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long
    
    @Update
    suspend fun updateTask(task: Task)
    
    @Delete
    suspend fun deleteTask(task: Task)
    
    @Query("DELETE FROM tasks WHERE isCompleted = 1")
    suspend fun deleteCompletedTasks()
} 