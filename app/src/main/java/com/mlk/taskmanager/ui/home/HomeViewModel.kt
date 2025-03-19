package com.mlk.taskmanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Project
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.repository.ProjectRepository
import com.mlk.taskmanager.data.repository.RoutineRepository
import com.mlk.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class HomeUiState(
    val assignedTasks: Int = 0,
    val completedTasks: Int = 0,
    val todayTasks: List<Task> = emptyList(),
    val todayRoutines: List<Routine> = emptyList(),
    val projects: List<Project> = emptyList(),
    val selectedFilter: TaskFilter = TaskFilter.ALL,
    val isLoading: Boolean = false,
    val error: String? = null,
    val showCreateProjectDialog: Boolean = false
)

enum class TaskFilter {
    ALL, IN_PROGRESS, COMPLETED
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val routineRepository: RoutineRepository,
    private val projectRepository: ProjectRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            try {
                combine(
                    taskRepository.getAllTasks(),
                    taskRepository.getActiveTasks(),
                    taskRepository.getTasksInTimeRange(
                        LocalDateTime.now().withHour(0).withMinute(0),
                        LocalDateTime.now().withHour(23).withMinute(59)
                    ),
                    routineRepository.getActiveRoutines(),
                    projectRepository.getAllProjects()
                ) { allTasks, activeTasks, todayTasks, routines, projects ->
                    _uiState.value.copy(
                        assignedTasks = activeTasks.size,
                        completedTasks = allTasks.count { it.isCompleted },
                        todayTasks = when (_uiState.value.selectedFilter) {
                            TaskFilter.ALL -> todayTasks
                            TaskFilter.IN_PROGRESS -> todayTasks.filter { !it.isCompleted }
                            TaskFilter.COMPLETED -> todayTasks.filter { it.isCompleted }
                        },
                        todayRoutines = routines,
                        projects = projects,
                        isLoading = false
                    )
                }.collect { state ->
                    _uiState.update { state }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message
                ) }
            }
        }
    }

    fun setTaskFilter(filter: TaskFilter) {
        _uiState.update { it.copy(selectedFilter = filter) }
        loadData()
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
    
    fun showCreateProjectDialog() {
        _uiState.update { it.copy(showCreateProjectDialog = true) }
    }
    
    fun hideCreateProjectDialog() {
        _uiState.update { it.copy(showCreateProjectDialog = false) }
    }
    
    fun createProject(name: String, description: String, icon: String) {
        viewModelScope.launch {
            try {
                val project = Project(
                    id = 0, // Auto-généré par Room
                    name = name,
                    description = description,
                    icon = icon,
                    color = listOf(0xFF613BE7, 0xFF4CAF50, 0xFFE91E63, 0xFFFF9800).random(),
                    taskCount = 0
                )
                projectRepository.insertProject(project)
                hideCreateProjectDialog()
                // Recharger les données pour voir le nouveau projet
                loadData()
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    error = "Erreur lors de la création du projet: ${e.message}"
                ) }
            }
        }
    }
} 