package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.dao.TaskDao
import com.mlk.taskmanager.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    
    override fun getActiveTasks(): Flow<List<Task>> = taskDao.getActiveTasks()
    
    override fun getLocationBasedTasks(): Flow<List<Task>> = taskDao.getLocationBasedTasks()
    
    override fun getTasksInTimeRange(start: LocalDateTime, end: LocalDateTime): Flow<List<Task>> =
        taskDao.getTasksInTimeRange(start, end)
    
    override suspend fun getTaskById(taskId: Long): Task? = taskDao.getTaskById(taskId)
    
    override suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)
    
    override suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    
    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    
    override suspend fun deleteCompletedTasks() = taskDao.deleteCompletedTasks()

    override suspend fun getTasksByDateRange(startDateTime: LocalDateTime, endDateTime: LocalDateTime): List<Task> =
        taskDao.getTasksByDateRange(startDateTime, endDateTime)
} 