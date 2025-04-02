package com.mlk.taskmanager.data.repository

import android.util.Log
import com.mlk.taskmanager.data.dao.ProjectDao
import com.mlk.taskmanager.data.model.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {
    
    override fun getAllProjects(): Flow<List<Project>> {
        Log.d("ProjectRepository", "Getting all projects")
        return projectDao.getAllProjects()
            .onEach { projects -> 
                Log.d("ProjectRepository", "Retrieved ${projects.size} projects: $projects")
            }
    }
    
    override suspend fun getProjectById(id: Long): Project? {
        Log.d("ProjectRepository", "Getting project by ID: $id")
        return projectDao.getProjectById(id).also { project ->
            Log.d("ProjectRepository", "Retrieved project: $project")
        }
    }
    
    override suspend fun insertProject(project: Project): Long {
        Log.d("ProjectRepository", "Inserting project: $project")
        return projectDao.insertProject(project).also { id ->
            Log.d("ProjectRepository", "Project inserted with ID: $id")
        }
    }
    
    override suspend fun updateProject(project: Project) {
        Log.d("ProjectRepository", "Updating project: $project")
        projectDao.updateProject(project)
        Log.d("ProjectRepository", "Project updated successfully")
    }
    
    override suspend fun deleteProject(id: Long) {
        Log.d("ProjectRepository", "Deleting project with ID: $id")
        projectDao.deleteProject(id)
        Log.d("ProjectRepository", "Project deleted successfully")
    }
    
    override suspend fun incrementTaskCount(projectId: Long) {
        Log.d("ProjectRepository", "Incrementing task count for project: $projectId")
        projectDao.incrementTaskCount(projectId)
        Log.d("ProjectRepository", "Task count incremented successfully")
    }
    
    override suspend fun decrementTaskCount(projectId: Long) {
        Log.d("ProjectRepository", "Decrementing task count for project: $projectId")
        projectDao.decrementTaskCount(projectId)
        Log.d("ProjectRepository", "Task count decremented successfully")
    }
} 