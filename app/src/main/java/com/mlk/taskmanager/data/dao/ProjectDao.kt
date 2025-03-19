package com.mlk.taskmanager.data.dao

import androidx.room.*
import com.mlk.taskmanager.data.model.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    fun getAllProjects(): Flow<List<Project>>
    
    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getProjectById(projectId: Long): Project?
    
    @Insert
    suspend fun insertProject(project: Project): Long
    
    @Update
    suspend fun updateProject(project: Project)
    
    @Query("DELETE FROM projects WHERE id = :projectId")
    suspend fun deleteProject(projectId: Long)
    
    @Query("UPDATE projects SET taskCount = taskCount + 1 WHERE id = :projectId")
    suspend fun incrementTaskCount(projectId: Long)
    
    @Query("UPDATE projects SET taskCount = MAX(0, taskCount - 1) WHERE id = :projectId")
    suspend fun decrementTaskCount(projectId: Long)
} 