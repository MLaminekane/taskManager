package com.mlk.taskmanager.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.repository.TaskRepository
import com.mlk.taskmanager.data.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState: StateFlow<CalendarUiState> = _uiState

    fun loadTasks(startDateTime: LocalDateTime, endDateTime: LocalDateTime) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                println("DEBUG: Loading tasks from $startDateTime to $endDateTime")
                val tasks = taskRepository.getTasksByDateRange(startDateTime, endDateTime)
                println("DEBUG: Loaded ${tasks.size} tasks")
                tasks.forEach { task ->
                    println("DEBUG: Task: ${task.title} due at ${task.dueDateTime}")
                }
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        tasks = tasks
                    )
                }
            } catch (e: Exception) {
                println("DEBUG: Error loading tasks: ${e.message}")
                e.printStackTrace()
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load tasks"
                    )
                }
            }
        }
    }
}

data class CalendarUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val error: String? = null
) 