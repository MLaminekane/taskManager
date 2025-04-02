package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun getAllProjects(): Flow<List<Project>>
    
    suspend fun getProjectById(id: Long): Project?
    
    suspend fun insertProject(project: Project): Long
    
    suspend fun updateProject(project: Project)
    
    suspend fun deleteProject(id: Long)
    
    suspend fun incrementTaskCount(projectId: Long)
    
    suspend fun decrementTaskCount(projectId: Long)
} 