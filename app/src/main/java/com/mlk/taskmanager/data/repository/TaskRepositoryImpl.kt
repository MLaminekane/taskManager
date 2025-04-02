package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.dao.TaskDao
import com.mlk.taskmanager.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val projectRepository: ProjectRepository
) : TaskRepository {
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    
    override fun getActiveTasks(): Flow<List<Task>> = taskDao.getActiveTasks()
    
    override fun getLocationBasedTasks(): Flow<List<Task>> = taskDao.getLocationBasedTasks()
    
    override fun getTasksInTimeRange(start: LocalDateTime, end: LocalDateTime): Flow<List<Task>> =
        taskDao.getTasksInTimeRange(start, end)
    
    override suspend fun getTaskById(taskId: Long): Task? = taskDao.getTaskById(taskId)
    
    override suspend fun insertTask(task: Task): Long {
        println("DEBUG: Repository - Inserting task: ${task.title}")
        try {
            val id = taskDao.insertTask(task)
            println("DEBUG: Repository - Task inserted successfully with ID: $id")
            
            // Incrémenter le compteur de tâches du projet si un projectId est spécifié
            task.projectId?.let { projectId ->
                projectRepository.incrementTaskCount(projectId)
            }
            
            return id
        } catch (e: Exception) {
            println("DEBUG: Repository - Error inserting task: ${e.message}")
            throw e
        }
    }
    
    override suspend fun updateTask(task: Task) {
        // Récupérer l'ancienne tâche pour vérifier si le projectId a changé
        val oldTask = taskDao.getTaskById(task.id)
        
        // Mettre à jour la tâche
        taskDao.updateTask(task)
        
        // Si le projectId a changé, mettre à jour les compteurs
        if (oldTask != null && oldTask.projectId != task.projectId) {
            // Décrémenter l'ancien projet
            oldTask.projectId?.let { projectId ->
                projectRepository.decrementTaskCount(projectId)
            }
            
            // Incrémenter le nouveau projet
            task.projectId?.let { projectId ->
                projectRepository.incrementTaskCount(projectId)
            }
        }
    }
    
    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
        
        // Décrémenter le compteur de tâches du projet si un projectId est spécifié
        task.projectId?.let { projectId ->
            projectRepository.decrementTaskCount(projectId)
        }
    }
    
    override suspend fun deleteCompletedTasks() {
        // Récupérer toutes les tâches complétées pour pouvoir décrémenter les compteurs
        val completedTasks = taskDao.getCompletedTasks()
        
        // Supprimer les tâches complétées
        taskDao.deleteCompletedTasks()
        
        // Décrémenter les compteurs de projets
        completedTasks.forEach { task ->
            task.projectId?.let { projectId ->
                projectRepository.decrementTaskCount(projectId)
            }
        }
    }

    override suspend fun getTasksByDateRange(startDateTime: LocalDateTime, endDateTime: LocalDateTime): List<Task> =
        taskDao.getTasksByDateRange(startDateTime, endDateTime)

    override suspend fun getAllTasksSync(): List<Task> = withContext(Dispatchers.IO) {
        // Récupération synchrone de toutes les tâches (sans Flow)
        taskDao.getAllTasksSync()
    }

    override fun getTasksByProject(projectId: Long): Flow<List<Task>> {
        return taskDao.getTasksByProject(projectId)
    }
} 