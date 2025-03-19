package com.mlk.taskmanager.ui.routines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.repository.RoutineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

data class RoutinesUiState(
    val routines: List<Routine> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class RoutinesViewModel @Inject constructor(
    private val routineRepository: RoutineRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(RoutinesUiState(isLoading = true))
    val uiState: StateFlow<RoutinesUiState> = _uiState.asStateFlow()
    
    init {
        loadRoutines()
    }
    
    private fun loadRoutines() {
        viewModelScope.launch {
            try {
                routineRepository.getAllRoutines()
                    .collect { routines ->
                        _uiState.update { it.copy(
                            routines = routines,
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
    
    fun addRoutine(
        title: String,
        description: String,
        time: LocalTime,
        repeatDays: Set<DayOfWeek>,
        category: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        locationRadius: Float? = null
    ) {
        viewModelScope.launch {
            try {
                // Pour l'instant, on stocke simplement la catégorie comme un entier long 
                // qui pourrait être converti plus tard en une entité de catégorie
                val categoryId: Long? = if (category != null) {
                    // On pourrait simplement utiliser un hashCode pour stocker la catégorie
                    // ou mettre en place un système plus robuste avec une table séparée
                    category.hashCode().toLong()
                } else {
                    null
                }
                
                val routine = Routine(
                    title = title,
                    description = description,
                    time = time,
                    repeatDays = repeatDays,
                    categoryId = categoryId,
                    latitude = latitude,
                    longitude = longitude,
                    locationRadius = locationRadius
                )
                
                routineRepository.insertRoutine(routine)
                loadRoutines()
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(error = e.message ?: "Failed to create routine") 
                }
            }
        }
    }
    
    fun updateRoutine(routine: Routine) {
        viewModelScope.launch {
            try {
                routineRepository.updateRoutine(routine)
                loadRoutines()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    fun toggleRoutineEnabled(routine: Routine) {
        viewModelScope.launch {
            try {
                routineRepository.updateRoutine(routine.copy(isEnabled = !routine.isEnabled))
                loadRoutines()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    fun deleteRoutine(routine: Routine) {
        viewModelScope.launch {
            try {
                routineRepository.deleteRoutine(routine)
                loadRoutines()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
} 