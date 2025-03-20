package com.mlk.taskmanager.ui.routines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.repository.RoutineRepository
import com.mlk.taskmanager.service.CalendarSyncService
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
    private val routineRepository: RoutineRepository,
    private val calendarSyncService: CalendarSyncService
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
                        _uiState.value = _uiState.value.copy(
                            routines = routines,
                            isLoading = false,
                            error = null
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
    
    fun addRoutine(
        title: String,
        description: String,
        time: LocalTime,
        repeatDays: List<DayOfWeek>,
        category: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        locationRadius: Float? = null
    ) {
        viewModelScope.launch {
            try {
                val routine = Routine(
                    title = title,
                    description = description,
                    time = time,
                    repeatDays = repeatDays,
                    category = category,
                    latitude = latitude,
                    longitude = longitude,
                    locationRadius = locationRadius,
                    isEnabled = true,
                    isSyncedWithCalendar = false,
                    calendarEventId = null
                )
                
                routineRepository.insertRoutine(routine)
                loadRoutines()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Failed to create routine"
                )
            }
        }
    }
    
    fun updateRoutine(routine: Routine) {
        viewModelScope.launch {
            try {
                routineRepository.updateRoutine(routine)
                loadRoutines()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
    
    fun toggleRoutineEnabled(routine: Routine) {
        viewModelScope.launch {
            try {
                routineRepository.updateRoutine(routine.copy(isEnabled = !routine.isEnabled))
                loadRoutines()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
    
    fun deleteRoutine(routine: Routine) {
        viewModelScope.launch {
            try {
                routineRepository.deleteRoutine(routine)
                loadRoutines()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
    
    fun toggleCalendarSync(routine: Routine) {
        viewModelScope.launch {
            try {
                if (routine.isSyncedWithCalendar) {
                    calendarSyncService.deleteCalendarEvent(routine)
                } else {
                    calendarSyncService.syncRoutineWithCalendar(routine)
                }
                loadRoutines()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
} 