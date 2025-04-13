package com.mlk.taskmanager.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

/**
 * État de l'interface utilisateur pour l'écran d'accueil
 *
 * @param assignedTasks Nombre de tâches assignées (non complétées)
 * @param completedTasks Nombre de tâches complétées
 * @param todayTasks Liste des tâches du jour
 * @param upcomingTasks Liste des tâches à venir
 * @param todayRoutines Liste des routines du jour
 * @param projects Liste des projets
 * @param selectedFilter Filtre de tâches sélectionné
 * @param isLoading Indique si les données sont en cours de chargement
 * @param error Message d'erreur éventuel
 * @param showCreateProjectDialog Afficher le dialogue de création de projet
 * @param weatherData Données météo
 * @param weatherLoading Indique si les données météo sont en cours de chargement
 * @param weatherError Message d'erreur pour les données météo
 * @param weatherModalVisible Afficher le modal météo
 * @param currentUser Nom de l'utilisateur actuel
 */
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

/**
 * Filtre pour les tâches dans l'écran d'accueil
 */
enum class TaskFilter {
    ALL, IN_PROGRESS, COMPLETED
}

/**
 * ViewModel pour l'écran d'accueil
 * Gère les données des tâches, routines, projets et informations météo
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val routineRepository: RoutineRepository,
    private val projectRepository: ProjectRepository,
    private val weatherRepository: WeatherRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    
    companion object {
        private const val TAG = "HomeViewModel"
        
        // Coordonnées par défaut pour la météo si la localisation n'est pas disponible
        private const val DEFAULT_LATITUDE = 48.8566
        private const val DEFAULT_LONGITUDE = 2.3522
    }
    
    // État de l'interface utilisateur
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    // Client de localisation fusionnée pour obtenir la position de l'utilisateur
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    init {
        initLocationClient()
        loadData()
    }
    
    /**
     * Initialise le client de localisation
     */
    private fun initLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    /**
     * Charge toutes les données nécessaires à l'écran d'accueil
     * (projets, tâches, routines) et initialise l'état de l'UI
     */
    fun loadData() {
        viewModelScope.launch {
            Log.d(TAG, "Chargement des données de l'écran d'accueil")
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                combine(
                    projectRepository.getAllProjects(),
                    taskRepository.getAllTasks(),
                    routineRepository.getRoutinesForDay(LocalDate.now().dayOfWeek)
                ) { projects, tasks, routines ->
                    Log.d(TAG, "Données reçues - Projets: ${projects.size}, Tâches: ${tasks.size}, Routines: ${routines.size}")
                    
                    // Variables pour les tâches filtrées
                    val now = LocalDateTime.now()
                    val todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
                    val todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
                    
                    // Tâches du jour (échéance aujourd'hui)
                    val todayTasksList = tasks.filter { 
                        it.dueDateTime.isAfter(todayStart) && it.dueDateTime.isBefore(todayEnd)
                    }.sortedBy { it.dueDateTime }
                    
                    // Tâches à venir (non terminées et avec une date d'échéance future)
                    val upcomingTasksList = tasks.filter { 
                        !it.isCompleted && it.dueDateTime.isAfter(now) 
                    }.sortedBy { it.dueDateTime }
                    
                    // Filtre les routines pour aujourd'hui
                    val todayRoutinesList = routines.filter { routine ->
                        routine.repeatDays.contains(LocalDate.now().dayOfWeek)
                    }
                    
                    // Mise à jour de l'état avec les données chargées
                    HomeUiState(
                        assignedTasks = tasks.count { !it.isCompleted },
                        completedTasks = tasks.count { it.isCompleted },
                        todayTasks = todayTasksList,
                        upcomingTasks = upcomingTasksList.take(5), // Limite aux 5 prochaines tâches
                        todayRoutines = todayRoutinesList,
                        projects = projects,
                        isLoading = false,
                        selectedFilter = _uiState.value.selectedFilter,
                        weatherData = _uiState.value.weatherData,
                        weatherLoading = _uiState.value.weatherLoading,
                        weatherError = _uiState.value.weatherError,
                        weatherModalVisible = _uiState.value.weatherModalVisible
                    )
                }.collect { newState ->
                    _uiState.value = newState
                    // Charge les données météo une fois que les autres données sont prêtes
                    if (!_uiState.value.weatherLoading && _uiState.value.weatherData == null) {
                        loadWeatherData()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors du chargement des données", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Erreur lors du chargement des données: ${e.message}"
                )
            }
        }
    }
    
    /**
     * Définit le filtre de tâches actif
     * @param filter Le filtre à appliquer
     */
    fun setTaskFilter(filter: TaskFilter) {
        Log.d(TAG, "Changement de filtre: $filter")
        _uiState.value = _uiState.value.copy(selectedFilter = filter)
    }
    
    /**
     * Efface le message d'erreur
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    
    /**
     * Affiche le dialogue de création de projet
     */
    fun showCreateProjectDialog() {
        _uiState.value = _uiState.value.copy(showCreateProjectDialog = true)
    }
    
    /**
     * Masque le dialogue de création de projet
     */
    fun hideCreateProjectDialog() {
        _uiState.value = _uiState.value.copy(showCreateProjectDialog = false)
    }
    
    /**
     * Crée un nouveau projet
     * @param name Nom du projet
     * @param description Description du projet
     * @param icon Icône du projet
     */
    fun createProject(name: String, description: String, icon: String) {
        if (name.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Le nom du projet ne peut pas être vide")
            return
        }
        
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAG, "Création d'un nouveau projet: $name")
                val newProject = Project(
                    name = name,
                    description = description,
                    icon = icon,
                    color = 0xFFFF8400L, // PrimaryColor
                    taskCount = 0
                )
                val projectId = projectRepository.insertProject(newProject)
                Log.d(TAG, "Projet créé avec l'ID: $projectId")
                
                // Masque le dialogue après la création
                _uiState.value = _uiState.value.copy(showCreateProjectDialog = false)
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de la création du projet", e)
                _uiState.value = _uiState.value.copy(
                    error = "Erreur lors de la création du projet: ${e.message}"
                )
            }
        }
    }
    
    /**
     * Charge les données météo basées sur la localisation de l'utilisateur
     */
    @SuppressLint("MissingPermission")
    fun loadWeatherData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(weatherLoading = true, weatherError = null)
            
            try {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        viewModelScope.launch {
                            location?.let {
                                Log.d(TAG, "Localisation obtenue: ${it.latitude}, ${it.longitude}")
                                fetchWeatherForLocation(it.latitude, it.longitude)
                            } ?: run {
                                Log.d(TAG, "Localisation non disponible, utilisation des coordonnées par défaut")
                                fetchWeatherForLocation(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        viewModelScope.launch {
                            Log.e(TAG, "Erreur de localisation", e)
                            _uiState.value = _uiState.value.copy(
                                weatherLoading = false,
                                weatherError = "Erreur de localisation: ${e.message}"
                            )
                        }
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors du chargement des données météo", e)
                _uiState.value = _uiState.value.copy(
                    weatherLoading = false,
                    weatherError = "Erreur: ${e.message}"
                )
            }
        }
    }
    
    /**
     * Récupère les données météo pour une localisation donnée
     * @param latitude Latitude
     * @param longitude Longitude
     */
    private fun fetchWeatherForLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Récupération des données météo pour: $latitude, $longitude")
                weatherRepository.getCurrentWeather(latitude, longitude)
                    .catch { e ->
                        Log.e(TAG, "Erreur lors de la récupération des données météo", e)
                        _uiState.value = _uiState.value.copy(
                            weatherLoading = false,
                            weatherError = "Erreur météo: ${e.message}"
                        )
                    }
                    .collect { result ->
                        result.onSuccess { weatherResponse ->
                            Log.d(TAG, "Données météo reçues: ${weatherResponse.name}")
                            _uiState.value = _uiState.value.copy(
                                weatherData = weatherResponse,
                                weatherLoading = false,
                                weatherError = null
                            )
                        }.onFailure { error ->
                            Log.e(TAG, "Échec de la récupération des données météo", error)
                            _uiState.value = _uiState.value.copy(
                                weatherLoading = false,
                                weatherError = "Erreur météo: ${error.message}"
                            )
                        }
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur inattendue lors de la récupération des données météo", e)
                _uiState.value = _uiState.value.copy(
                    weatherLoading = false,
                    weatherError = "Erreur inattendue: ${e.message}"
                )
            }
        }
    }
    
    /**
     * Bascule la visibilité du modal météo
     */
    fun toggleWeatherModal() {
        _uiState.value = _uiState.value.copy(
            weatherModalVisible = !_uiState.value.weatherModalVisible
        )
    }
}