package com.mlk.taskmanager.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class TasksUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(TasksUiState(isLoading = true))
    val uiState: StateFlow<TasksUiState> = _uiState.asStateFlow()
    
    init {
        loadTasks()
    }
    
    private fun loadTasks() {
        viewModelScope.launch {
            try {
                taskRepository.getActiveTasks()
                    .collect { tasks ->
                        _uiState.update { it.copy(
                            tasks = tasks,
                            isLoading = false,
                            error = null
                        ) }
                    }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message
                ) }
            }
        }
    }
    
    fun addTask(
        title: String,
        description: String,
        dueDateTime: LocalDateTime,
        priority: Priority,
        category: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        locationRadius: Float? = null
    ) {
        viewModelScope.launch {
            try {
                println("DEBUG: Starting task creation process")
                println("DEBUG: Creating task with title: $title, due date: $dueDateTime, category: $category")
                
                val task = Task(
                    title = title,
                    description = description,
                    dueDateTime = dueDateTime,
                    priority = priority,
                    category = category,
                    latitude = latitude,
                    longitude = longitude,
                    locationRadius = locationRadius
                )
                
                println("DEBUG: Task object created, inserting into database")
                val taskId = taskRepository.insertTask(task)
                println("DEBUG: Task inserted successfully with ID: $taskId")
                
                loadTasks()
                println("DEBUG: Tasks reloaded after insertion")
            } catch (e: Exception) {
                println("DEBUG: Error creating task: ${e.message}")
                e.printStackTrace()
                _uiState.update { 
                    it.copy(error = e.message ?: "Failed to create task") 
                }
            }
        }
    }
    
    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            try {
                taskRepository.updateTask(task.copy(isCompleted = !task.isCompleted))
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                taskRepository.deleteTask(task)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
} 