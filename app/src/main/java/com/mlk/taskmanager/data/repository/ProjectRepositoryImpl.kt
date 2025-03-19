package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.dao.ProjectDao
import com.mlk.taskmanager.data.model.Project
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {
    
    override fun getAllProjects(): Flow<List<Project>> {
        return projectDao.getAllProjects()
    }
    
    override suspend fun getProjectById(id: Int): Project? {
        return projectDao.getProjectById(id.toLong())
    }
    
    override suspend fun insertProject(project: Project): Long {
        return projectDao.insertProject(project)
    }
    
    override suspend fun updateProject(project: Project) {
        projectDao.updateProject(project)
    }
    
    override suspend fun deleteProject(id: Int) {
        projectDao.deleteProject(id.toLong())
    }
    
    override suspend fun incrementTaskCount(projectId: Int) {
        projectDao.incrementTaskCount(projectId.toLong())
    }
    
    override suspend fun decrementTaskCount(projectId: Int) {
        projectDao.decrementTaskCount(projectId.toLong())
    }
} 