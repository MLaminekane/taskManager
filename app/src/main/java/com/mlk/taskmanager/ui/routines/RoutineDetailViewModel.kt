package com.mlk.taskmanager.ui.routines

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.repository.RoutineRepository
import com.mlk.taskmanager.data.repository.SettingsRepository
import com.mlk.taskmanager.service.CalendarSyncService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

data class RoutineDetailUiState(
    val routine: Routine? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSyncing: Boolean = false,
    val syncError: String? = null
)

@HiltViewModel
class RoutineDetailViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val calendarSyncService: CalendarSyncService,
    private val settingsRepository: SettingsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val routineId: Long = savedStateHandle.get<Long>("routineId") ?: -1L

    private val _uiState = MutableStateFlow(RoutineDetailUiState(isLoading = true))
    val uiState: StateFlow<RoutineDetailUiState> = _uiState.asStateFlow()

    init {
        loadRoutine()
    }

    private fun loadRoutine() {
        viewModelScope.launch {
            _uiState.value = RoutineDetailUiState(isLoading = true)

            try {
                routineRepository.getRoutineById(routineId).collect { routine ->
                    if (routine != null) {
                        _uiState.value = RoutineDetailUiState(routine = routine)
                    } else {
                        _uiState.value = RoutineDetailUiState(error = "Routine not found")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = RoutineDetailUiState(error = e.message)
            }
        }
    }

    fun toggleCalendarSync() {
        val routine = _uiState.value.routine ?: return
        
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isSyncing = true, syncError = null)
                
                // Vérifier si les paramètres de sync sont activés
                val isSyncEnabled = settingsRepository.isCalendarSyncEnabled().first()
                
                if (!isSyncEnabled) {
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        syncError = "La synchronisation Google Calendar n'est pas activée dans les paramètres"
                    )
                    return@launch
                }
                
                // Vérifier si l'utilisateur est connecté
                if (!calendarSyncService.isSignedIn()) {
                    _uiState.value = _uiState.value.copy(
                        isSyncing = false,
                        syncError = "Vous n'êtes pas connecté à Google. Connectez-vous dans les paramètres."
                    )
                    return@launch
                }
                
                // Si déjà synchronisé, supprimer de Google Calendar
                if (routine.isSyncedWithCalendar) {
                    calendarSyncService.deleteCalendarEvent(routine)
                        .onSuccess {
                            val updatedRoutine = routine.copy(
                                calendarEventId = null,
                                isSyncedWithCalendar = false
                            )
                            routineRepository.updateRoutine(updatedRoutine)
                            _uiState.value = _uiState.value.copy(
                                routine = updatedRoutine,
                                isSyncing = false,
                                syncError = null
                            )
                        }
                        .onFailure { error ->
                            _uiState.value = _uiState.value.copy(
                                isSyncing = false,
                                syncError = "Erreur de désynchronisation: ${error.message}"
                            )
                        }
                } else {
                    // Sinon, synchroniser avec Google Calendar
                    calendarSyncService.syncRoutineWithCalendar(routine)
                        .onSuccess { updatedRoutine ->
                            _uiState.value = _uiState.value.copy(
                                routine = updatedRoutine,
                                isSyncing = false,
                                syncError = null
                            )
                        }
                        .onFailure { error ->
                            _uiState.value = _uiState.value.copy(
                                isSyncing = false,
                                syncError = "Erreur de synchronisation: ${error.message}"
                            )
                        }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSyncing = false,
                    syncError = "Erreur: ${e.message}"
                )
            }
        }
    }

    fun updateRoutine(
        title: String,
        description: String,
        time: LocalTime,
        repeatDays: List<DayOfWeek>,
        isEnabled: Boolean
    ) {
        val currentRoutine = _uiState.value.routine ?: return
        
        viewModelScope.launch {
            try {
                val updatedRoutine = currentRoutine.copy(
                    title = title,
                    description = description,
                    time = time,
                    repeatDays = repeatDays,
                    isEnabled = isEnabled
                )
                
                routineRepository.updateRoutine(updatedRoutine)
                
                // Si la routine est synchronisée avec Google Calendar, mettre à jour l'événement
                if (updatedRoutine.isSyncedWithCalendar) {
                    _uiState.value = _uiState.value.copy(isSyncing = true)
                    
                    calendarSyncService.updateCalendarEvent(updatedRoutine)
                        .onSuccess { syncedRoutine ->
                            _uiState.value = _uiState.value.copy(
                                routine = syncedRoutine,
                                isSyncing = false,
                                syncError = null
                            )
                        }
                        .onFailure { error ->
                            _uiState.value = _uiState.value.copy(
                                routine = updatedRoutine,
                                isSyncing = false,
                                syncError = "Erreur de mise à jour dans Google Calendar: ${error.message}"
                            )
                        }
                } else {
                    _uiState.value = _uiState.value.copy(routine = updatedRoutine)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message
                )
            }
        }
    }

    fun deleteRoutine() {
        val routine = _uiState.value.routine ?: return
        
        viewModelScope.launch {
            try {
                // Si synchronisé, supprimer d'abord de Google Calendar
                if (routine.isSyncedWithCalendar) {
                    calendarSyncService.deleteCalendarEvent(routine)
                }
                
                routineRepository.deleteRoutine(routine)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message
                )
            }
        }
    }

    fun toggleEnabled() {
        val routine = _uiState.value.routine ?: return
        
        viewModelScope.launch {
            try {
                val updatedRoutine = routine.copy(isEnabled = !routine.isEnabled)
                routineRepository.updateRoutine(updatedRoutine)
                _uiState.value = _uiState.value.copy(routine = updatedRoutine)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message
                )
            }
        }
    }
} 