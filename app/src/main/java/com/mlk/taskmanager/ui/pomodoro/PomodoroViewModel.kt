package com.mlk.taskmanager.ui.pomodoro

import android.app.NotificationManager
import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PomodoroUiState(
    val minutes: Int = 25,
    val seconds: Int = 0,
    val isRunning: Boolean = false,
    val isBreak: Boolean = false,
    val dndEnabled: Boolean = false,
    val notificationBlocked: Boolean = false,
    val completedSessions: Int = 0,
    val totalFocusMinutes: Int = 0,
    val focusRate: Int = 0
)

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(PomodoroUiState())
    val uiState: StateFlow<PomodoroUiState> = _uiState

    private var timer: CountDownTimer? = null
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun startCountdown(minutes: Int) {
        try {
            timer?.cancel()
            
            val totalMillis = minutes * 60 * 1000L
            timer = object : CountDownTimer(totalMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    viewModelScope.launch {
                        val minutesRemaining = (millisUntilFinished / 1000 / 60).toInt()
                        val secondsRemaining = ((millisUntilFinished / 1000) % 60).toInt()
                        _uiState.update { it.copy(
                            minutes = minutesRemaining,
                            seconds = secondsRemaining
                        ) }
                    }
                }

                override fun onFinish() {
                    viewModelScope.launch {
                        if (!_uiState.value.isBreak) {
                            // Session completed
                            _uiState.update { it.copy(
                                completedSessions = it.completedSessions + 1,
                                totalFocusMinutes = it.totalFocusMinutes + 25,
                                focusRate = calculateFocusRate(it.completedSessions + 1, it.totalFocusMinutes + 25),
                                isBreak = true,
                                minutes = 5,
                                seconds = 0,
                                isRunning = false
                            ) }
                        } else {
                            // Break completed
                            _uiState.update { it.copy(
                                isBreak = false,
                                minutes = 25,
                                seconds = 0,
                                isRunning = false
                            ) }
                        }
                        updateNotificationSettings()
                    }
                }
            }.start()

            _uiState.update { it.copy(isRunning = true) }
            updateNotificationSettings()
        } catch (e: Exception) {
            Log.e("PomodoroViewModel", "Error starting countdown", e)
            resetTimer()
        }
    }

    private fun calculateFocusRate(sessions: Int, totalMinutes: Int): Int {
        if (sessions == 0) return 0
        val expectedMinutes = sessions * 25
        return ((totalMinutes.toFloat() / expectedMinutes.toFloat()) * 100).toInt()
    }

    fun startTimer() {
        viewModelScope.launch {
            try {
                startCountdown(_uiState.value.minutes)
            } catch (e: Exception) {
                Log.e("PomodoroViewModel", "Error in startTimer", e)
                resetTimer()
            }
        }
    }

    fun pauseTimer() {
        viewModelScope.launch {
            try {
                timer?.cancel()
                _uiState.update { it.copy(isRunning = false) }
                updateNotificationSettings()
            } catch (e: Exception) {
                Log.e("PomodoroViewModel", "Error in pauseTimer", e)
            }
        }
    }

    fun resetTimer() {
        viewModelScope.launch {
            try {
                timer?.cancel()
                _uiState.update { it.copy(
                    minutes = if (_uiState.value.isBreak) 5 else 25,
                    seconds = 0,
                    isRunning = false
                ) }
                updateNotificationSettings()
            } catch (e: Exception) {
                Log.e("PomodoroViewModel", "Error in resetTimer", e)
            }
        }
    }

    fun skipSession() {
        viewModelScope.launch {
            try {
                timer?.cancel()
                if (_uiState.value.isBreak) {
                    _uiState.update { it.copy(
                        isBreak = false,
                        minutes = 25,
                        seconds = 0,
                        isRunning = false
                    ) }
                } else {
                    _uiState.update { it.copy(
                        isBreak = true,
                        minutes = 5,
                        seconds = 0,
                        isRunning = false,
                        completedSessions = it.completedSessions + 1,
                        totalFocusMinutes = it.totalFocusMinutes + ((25 - it.minutes) + (it.seconds / 60))
                    ) }
                }
                updateNotificationSettings()
            } catch (e: Exception) {
                Log.e("PomodoroViewModel", "Error in skipSession", e)
            }
        }
    }

    fun toggleDnd() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(dndEnabled = !it.dndEnabled) }
                updateNotificationSettings()
            } catch (e: Exception) {
                Log.e("PomodoroViewModel", "Error in toggleDnd", e)
            }
        }
    }

    fun toggleNotificationBlocking() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(notificationBlocked = !it.notificationBlocked) }
                updateNotificationSettings()
            } catch (e: Exception) {
                Log.e("PomodoroViewModel", "Error in toggleNotificationBlocking", e)
            }
        }
    }

    private fun updateNotificationSettings() {
        try {
            if (_uiState.value.isRunning && !_uiState.value.isBreak) {
                if (_uiState.value.dndEnabled) {
                    notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
                }
            } else {
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
            }
        } catch (e: Exception) {
            Log.e("PomodoroViewModel", "Error in updateNotificationSettings", e)
        }
    }

    override fun onCleared() {
        super.onCleared()
        try {
            timer?.cancel()
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
        } catch (e: Exception) {
            Log.e("PomodoroViewModel", "Error in onCleared", e)
        }
    }
} 