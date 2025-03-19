package com.mlk.taskmanager.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.repository.TaskRepository
import com.mlk.taskmanager.service.LocationReminderService
import com.mlk.taskmanager.service.NotificationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

// Enum pour les options de tri
enum class SortOption {
    DATE_ASC, DATE_DESC, PRIORITY_HIGH, PRIORITY_LOW, TITLE_ASC, TITLE_DESC
}

data class TasksUiState(
    val tasks: List<Task> = emptyList(),
    val filteredTasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val isSearchActive: Boolean = false,
    val selectedPriorities: Set<Priority> = setOf(Priority.LOW, Priority.MEDIUM, Priority.HIGH),
    val showCompletedTasks: Boolean = true,
    val sortOption: SortOption = SortOption.DATE_ASC,
    val isFilterDialogVisible: Boolean = false
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val locationReminderService: LocationReminderService,
    private val notificationManager: NotificationManager
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(TasksUiState(isLoading = true))
    val uiState: StateFlow<TasksUiState> = _uiState.asStateFlow()
    
    init {
        loadTasks()
    }
    
    private fun loadTasks() {
        viewModelScope.launch {
            try {
                taskRepository.getAllTasks()
                    .collect { tasks ->
                        _uiState.update { currentState ->
                            val filteredTasks = applyFilters(tasks, currentState)
                            currentState.copy(
                                tasks = tasks,
                                filteredTasks = filteredTasks,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message
                ) }
            }
        }
    }
    
    private fun applyFilters(tasks: List<Task>, state: TasksUiState): List<Task> {
        var result = tasks
        
        // Filtrer par statut (complété ou non)
        if (!state.showCompletedTasks) {
            result = result.filter { !it.isCompleted }
        }
        
        // Filtrer par priorité
        result = result.filter { state.selectedPriorities.contains(it.priority) }
        
        // Filtrer par recherche
        if (state.searchQuery.isNotEmpty()) {
            result = result.filter { 
                it.title.contains(state.searchQuery, ignoreCase = true) || 
                it.description.contains(state.searchQuery, ignoreCase = true)
            }
        }
        
        // Appliquer le tri
        result = when (state.sortOption) {
            SortOption.DATE_ASC -> result.sortedBy { it.dueDateTime }
            SortOption.DATE_DESC -> result.sortedByDescending { it.dueDateTime }
            SortOption.PRIORITY_HIGH -> result.sortedByDescending { it.priority }
            SortOption.PRIORITY_LOW -> result.sortedBy { it.priority }
            SortOption.TITLE_ASC -> result.sortedBy { it.title }
            SortOption.TITLE_DESC -> result.sortedByDescending { it.title }
        }
        
        return result
    }
    
    // Méthodes pour la recherche
    fun setSearchQuery(query: String) {
        _uiState.update { currentState ->
            val updatedState = currentState.copy(searchQuery = query)
            val filteredTasks = applyFilters(currentState.tasks, updatedState)
            updatedState.copy(filteredTasks = filteredTasks)
        }
    }
    
    fun toggleSearchActive() {
        _uiState.update { it.copy(
            isSearchActive = !it.isSearchActive,
            searchQuery = if (it.isSearchActive) "" else it.searchQuery
        ) }
        
        if (!_uiState.value.isSearchActive) {
            // Si on désactive la recherche, on réapplique les filtres sans la recherche
            updateFilteredTasks()
        }
    }
    
    // Méthodes pour les filtres
    fun toggleFilterDialog() {
        _uiState.update { it.copy(isFilterDialogVisible = !it.isFilterDialogVisible) }
    }
    
    fun togglePriorityFilter(priority: Priority) {
        _uiState.update { currentState ->
            val updatedPriorities = currentState.selectedPriorities.toMutableSet()
            if (updatedPriorities.contains(priority)) {
                updatedPriorities.remove(priority)
            } else {
                updatedPriorities.add(priority)
            }
            
            val updatedState = currentState.copy(selectedPriorities = updatedPriorities)
            val filteredTasks = applyFilters(currentState.tasks, updatedState)
            updatedState.copy(filteredTasks = filteredTasks)
        }
    }
    
    fun toggleShowCompletedTasks() {
        _uiState.update { currentState ->
            val updatedState = currentState.copy(showCompletedTasks = !currentState.showCompletedTasks)
            val filteredTasks = applyFilters(currentState.tasks, updatedState)
            updatedState.copy(filteredTasks = filteredTasks)
        }
    }
    
    fun setSortOption(option: SortOption) {
        _uiState.update { currentState ->
            val updatedState = currentState.copy(sortOption = option)
            val filteredTasks = applyFilters(currentState.tasks, updatedState)
            updatedState.copy(filteredTasks = filteredTasks)
        }
    }
    
    private fun updateFilteredTasks() {
        _uiState.update { currentState ->
            val filteredTasks = applyFilters(currentState.tasks, currentState)
            currentState.copy(filteredTasks = filteredTasks)
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
        locationRadius: Float? = null,
        projectId: Long? = null
    ) {
        viewModelScope.launch {
            try {
                println("DEBUG: Starting task creation process")
                println("DEBUG: Creating task with title: $title, due date: $dueDateTime, category: $category, projectId: $projectId")
                
                val task = Task(
                    title = title,
                    description = description,
                    dueDateTime = dueDateTime,
                    priority = priority,
                    category = category,
                    latitude = latitude,
                    longitude = longitude,
                    locationRadius = locationRadius,
                    projectId = projectId
                )
                
                println("DEBUG: Task object created, inserting into database")
                val taskId = taskRepository.insertTask(task)
                println("DEBUG: Task inserted successfully with ID: $taskId")
                
                // Utiliser le gestionnaire de notifications pour configurer toutes les notifications
                val taskWithId = task.copy(id = taskId)
                notificationManager.scheduleTaskNotifications(taskWithId)
                println("DEBUG: Notifications scheduled for task")
                
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
    
    // Méthode pour rafraîchir l'interface après un court délai
    fun delayedRefresh() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(300) // attendre 300ms pour voir l'animation
            loadTasks()
        }
    }
} 