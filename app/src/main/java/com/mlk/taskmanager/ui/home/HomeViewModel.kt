package com.mlk.taskmanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class Project(
    val id: Long,
    val name: String,
    val description: String,
    val icon: String,
    val color: Long,
    val taskCount: Int = 0
)

data class HomeUiState(
    val assignedTasks: Int = 0,
    val completedTasks: Int = 0,
    val todayTasks: List<Task> = emptyList(),
    val projects: List<Project> = listOf(
        Project(1, "Mobile App", "Application mobile Android", "kotlin", 0xFF613BE7, 5),
        Project(2, "Web App", "Application web React", "typescript", 0xFF4CAF50, 3)
    ),
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
    private val taskRepository: TaskRepository
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
                    )
                ) { allTasks, activeTasks, todayTasks ->
                    _uiState.value.copy(
                        assignedTasks = activeTasks.size,
                        completedTasks = allTasks.count { it.isCompleted },
                        todayTasks = when (_uiState.value.selectedFilter) {
                            TaskFilter.ALL -> todayTasks
                            TaskFilter.IN_PROGRESS -> todayTasks.filter { !it.isCompleted }
                            TaskFilter.COMPLETED -> todayTasks.filter { it.isCompleted }
                        },
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
        val newProject = Project(
            id = (_uiState.value.projects.maxOfOrNull { it.id } ?: 0) + 1,
            name = name,
            description = description,
            icon = icon,
            color = when (icon) {
                "kotlin" -> 0xFF613BE7
                "typescript" -> 0xFF4CAF50
                else -> 0xFF666666
            }
        )
        
        _uiState.update { state ->
            state.copy(
                projects = state.projects + newProject,
                showCreateProjectDialog = false
            )
        }
    }
} 