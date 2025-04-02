package com.mlk.taskmanager.ui.project

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Project
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.repository.ProjectRepository
import com.mlk.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProjectDetailUiState(
    val project: Project? = null,
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProjectDetailViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProjectDetailUiState())
    val uiState: StateFlow<ProjectDetailUiState> = _uiState

    fun loadProject(projectId: Long) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null) }
                Log.d("ProjectDetailViewModel", "Loading project with ID: $projectId")
                
                val project = projectRepository.getProjectById(projectId)
                if (project == null) {
                    Log.e("ProjectDetailViewModel", "Project not found for ID: $projectId")
                    _uiState.update { it.copy(
                        isLoading = false,
                        error = "Projet non trouvé"
                    ) }
                    return@launch
                }
                
                Log.d("ProjectDetailViewModel", "Project found: ${project.name}")
                _uiState.update { it.copy(
                    project = project,
                    isLoading = false
                ) }
                
                try {
                    taskRepository.getTasksByProject(projectId).collect { tasks ->
                        Log.d("ProjectDetailViewModel", "Tasks loaded: ${tasks.size}")
                        _uiState.update { it.copy(
                            tasks = tasks,
                            isLoading = false
                        ) }
                    }
                } catch (e: Exception) {
                    Log.e("ProjectDetailViewModel", "Error loading tasks: ${e.message}", e)
                    _uiState.update { it.copy(
                        error = "Erreur lors du chargement des tâches: ${e.message}",
                        isLoading = false
                    ) }
                }
            } catch (e: Exception) {
                Log.e("ProjectDetailViewModel", "Error loading project: ${e.message}", e)
                _uiState.update { it.copy(
                    error = "Erreur lors du chargement du projet: ${e.message}",
                    isLoading = false
                ) }
            }
        }
    }
} 