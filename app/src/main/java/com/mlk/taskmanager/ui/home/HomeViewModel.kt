package com.mlk.taskmanager.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mlk.taskmanager.data.model.Project
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.model.WeatherResponse
import com.mlk.taskmanager.data.repository.ProjectRepository
import com.mlk.taskmanager.data.repository.RoutineRepository
import com.mlk.taskmanager.data.repository.TaskRepository
import com.mlk.taskmanager.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

data class HomeUiState(
    val assignedTasks: Int = 0,
    val completedTasks: Int = 0,
    val todayTasks: List<Task> = emptyList(),
    val upcomingTasks: List<Task> = emptyList(),
    val todayRoutines: List<Routine> = emptyList(),
    val projects: List<Project> = emptyList(),
    val selectedFilter: TaskFilter = TaskFilter.ALL,
    val isLoading: Boolean = false,
    val error: String? = null,
    val showCreateProjectDialog: Boolean = false,
    val weatherData: WeatherResponse? = null,
    val weatherLoading: Boolean = false,
    val weatherError: String? = null,
    val weatherModalVisible: Boolean = false,
    val currentUser: String = "Lamine"
)

enum class TaskFilter {
    ALL, IN_PROGRESS, COMPLETED
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val routineRepository: RoutineRepository,
    private val projectRepository: ProjectRepository,
    private val weatherRepository: WeatherRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        
        viewModelScope.launch {
            Log.d("HomeViewModel", "Starting to collect data streams")
            combine(
                projectRepository.getAllProjects(),
                taskRepository.getAllTasks(),
                routineRepository.getRoutinesForDay(LocalDate.now().dayOfWeek)
            ) { projects, tasks, routines ->
                Log.d("HomeViewModel", "Data received - Projects: ${projects.size}, Tasks: ${tasks.size}, Routines: ${routines.size}")
                Log.d("HomeViewModel", "Projects: $projects")
                
                // Filtrer les tâches à venir (non terminées et avec une date d'échéance future)
                val now = LocalDateTime.now()
                val upcomingTasksList = tasks.filter { 
                    !it.isCompleted && it.dueDateTime.isAfter(now) 
                }.sortedBy { it.dueDateTime }
                
                HomeUiState(
                    assignedTasks = tasks.count { !it.isCompleted },
                    completedTasks = tasks.count { it.isCompleted },
                    todayTasks = tasks,
                    upcomingTasks = upcomingTasksList,
                    todayRoutines = routines,
                    projects = projects,
                    weatherData = _uiState.value.weatherData,
                    weatherLoading = _uiState.value.weatherLoading,
                    weatherError = _uiState.value.weatherError,
                    weatherModalVisible = _uiState.value.weatherModalVisible,
                    currentUser = "Lamine" // Valeur par défaut, à remplacer par le nom réel de l'utilisateur si disponible
                )
            }.collect { newState ->
                Log.d("HomeViewModel", "Updating UI state with ${newState.projects.size} projects")
                _uiState.value = newState
            }
        }
        
        loadWeatherData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            // Mettre à jour l'état pour indiquer le chargement
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                // Récupérer toutes les données nécessaires
                val allTasks = taskRepository.getAllTasks().first()
                val activeTasks = taskRepository.getActiveTasks().first()
                val todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0)
                val todayEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59)
                
                // Récupérer les tâches d'aujourd'hui directement via une requête simple
                val todayTasks = allTasks.filter { task ->
                    val taskDate = task.dueDateTime
                    taskDate.isAfter(todayStart) && taskDate.isBefore(todayEnd)
                }
                
                // Filtrer les tâches à venir (non terminées et avec une date d'échéance future)
                val now = LocalDateTime.now()
                val upcomingTasks = allTasks.filter { 
                    !it.isCompleted && it.dueDateTime.isAfter(now) 
                }.sortedBy { it.dueDateTime }
                
                // Appliquer le filtre actuel
                val filteredTasks = when (_uiState.value.selectedFilter) {
                    TaskFilter.ALL -> todayTasks
                    TaskFilter.IN_PROGRESS -> todayTasks.filter { !it.isCompleted }
                    TaskFilter.COMPLETED -> todayTasks.filter { it.isCompleted }
                }
                
                // Afficher un message de débogage
                println("DEBUG: Loaded ${todayTasks.size} tasks for today, filtered to ${filteredTasks.size} tasks")
                
                // Récupérer les autres données
                val routines = routineRepository.getActiveRoutines().first()
                val projects = projectRepository.getAllProjects().first()
                
                // Mettre à jour l'état
                _uiState.value = _uiState.value.copy(
                    assignedTasks = activeTasks.size,
                    completedTasks = allTasks.count { it.isCompleted },
                    todayTasks = filteredTasks,
                    upcomingTasks = upcomingTasks,
                    todayRoutines = routines,
                    projects = projects,
                    isLoading = false
                )
            } catch (e: Exception) {
                println("DEBUG ERROR: ${e.message}")
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun setTaskFilter(filter: TaskFilter) {
        _uiState.value = _uiState.value.copy(selectedFilter = filter)
        
        // Recharger complètement les données pour garantir que les filtres fonctionnent
        loadData()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    
    fun showCreateProjectDialog() {
        _uiState.value = _uiState.value.copy(showCreateProjectDialog = true)
    }
    
    fun hideCreateProjectDialog() {
        _uiState.value = _uiState.value.copy(showCreateProjectDialog = false)
    }
    
    fun createProject(name: String, description: String, icon: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("HomeViewModel", "Creating new project: $name")
                val newProject = Project(
                    name = name,
                    description = description,
                    icon = icon,
                    color = 0xFF613BE7L,
                    taskCount = 0
                )
                val projectId = projectRepository.insertProject(newProject)
                Log.d("HomeViewModel", "Project created with ID: $projectId")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error creating project", e)
            }
        }
    }
    
    @SuppressLint("MissingPermission")
    fun loadWeatherData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(weatherLoading = true, weatherError = null)
            
            try {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        viewModelScope.launch {
                            location?.let {
                                fetchWeatherForLocation(it.latitude, it.longitude)
                            } ?: run {
                                // Utiliser des coordonnées par défaut si la localisation n'est pas disponible
                                fetchWeatherForLocation(48.8566, 2.3522) // Paris par défaut
                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        viewModelScope.launch {
                            _uiState.value = _uiState.value.copy(
                                weatherLoading = false,
                                weatherError = "Erreur de localisation: ${e.message}"
                            )
                        }
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    weatherLoading = false,
                    weatherError = "Erreur: ${e.message}"
                )
            }
        }
    }
    
    private fun fetchWeatherForLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                weatherRepository.getCurrentWeather(latitude, longitude)
                    .catch { e ->
                        _uiState.value = _uiState.value.copy(
                            weatherLoading = false,
                            weatherError = "Erreur météo: ${e.message}"
                        )
                    }
                    .collect { result ->
                        result.onSuccess { weatherResponse ->
                            _uiState.value = _uiState.value.copy(
                                weatherData = weatherResponse,
                                weatherLoading = false,
                                weatherError = null
                            )
                        }.onFailure { error ->
                            _uiState.value = _uiState.value.copy(
                                weatherLoading = false,
                                weatherError = "Erreur météo: ${error.message}"
                            )
                        }
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    weatherLoading = false,
                    weatherError = "Erreur inattendue: ${e.message}"
                )
            }
        }
    }
    
    fun toggleWeatherModal() {
        _uiState.value = _uiState.value.copy(
            weatherModalVisible = !_uiState.value.weatherModalVisible
        )
    }
} 