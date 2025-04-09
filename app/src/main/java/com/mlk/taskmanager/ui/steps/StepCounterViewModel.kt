package com.mlk.taskmanager.ui.steps

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.service.StepCounterService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StepCounterUiState(
    val steps: Int = 0,
    val distance: Float = 0f,
    val isServiceBound: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class StepCounterViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(StepCounterUiState())
    val uiState: StateFlow<StepCounterUiState> = _uiState
    
    private var stepCounterService: StepCounterService? = null
    private var isBound = false
    
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as StepCounterService.StepCounterBinder
            stepCounterService = binder.getService()
            isBound = true
            _uiState.update { it.copy(isServiceBound = true) }
            Log.d("StepCounterViewModel", "Service connected")
        }
        
        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
            _uiState.update { it.copy(isServiceBound = false) }
            Log.d("StepCounterViewModel", "Service disconnected")
        }
    }
    
    init {
        startStepCounter()
    }
    
    private fun startStepCounter() {
        try {
            val intent = Intent(context, StepCounterService::class.java)
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
            Log.d("StepCounterViewModel", "Step counter service started")
        } catch (e: Exception) {
            Log.e("StepCounterViewModel", "Error starting step counter: ${e.message}", e)
            _uiState.update { it.copy(error = e.message) }
        }
    }
    
    fun updateStepCount() {
        viewModelScope.launch {
            try {
                stepCounterService?.let { service ->
                    val steps = service.getStepCount()
                    val distance = service.getDistanceInMeters()
                    _uiState.update { it.copy(steps = steps, distance = distance) }
                }
            } catch (e: Exception) {
                Log.e("StepCounterViewModel", "Error updating step count: ${e.message}", e)
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    fun resetCounter() {
        viewModelScope.launch {
            try {
                stepCounterService?.resetCounter()
                _uiState.update { it.copy(steps = 0, distance = 0f) }
            } catch (e: Exception) {
                Log.e("StepCounterViewModel", "Error resetting counter: ${e.message}", e)
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        if (isBound) {
            context.unbindService(connection)
            isBound = false
        }
    }
} 