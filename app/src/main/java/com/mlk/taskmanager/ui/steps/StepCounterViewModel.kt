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

/**
 * État de l'interface utilisateur pour le compteur de pas
 * @param steps Nombre de pas comptabilisés
 * @param distance Distance parcourue en mètres
 * @param isServiceBound Indique si le service de comptage est actif
 * @param isTracking Indique si le suivi des pas est actif
 * @param error Message d'erreur, null si aucune erreur
 */
data class StepCounterUiState(
    val steps: Int = 0,
    val distance: Float = 0f,
    val isServiceBound: Boolean = false,
    val isTracking: Boolean = false,
    val error: String? = null
)

/**
 * ViewModel pour le compteur de pas
 * Gère la communication avec le service de détection des pas et maintient l'état de l'UI
 */
@HiltViewModel
class StepCounterViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    
    // État de l'interface utilisateur
    private val _uiState = MutableStateFlow(StepCounterUiState())
    val uiState: StateFlow<StepCounterUiState> = _uiState
    
    // Référence au service de comptage des pas
    private var stepCounterService: StepCounterService? = null
    private var isBound = false
    
    /**
     * Connexion au service de comptage des pas
     */
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // Récupération de la référence au service
            val binder = service as StepCounterService.StepCounterBinder
            stepCounterService = binder.getService()
            isBound = true
            _uiState.update { it.copy(isServiceBound = true) }
            Log.d("StepCounterViewModel", "Service connected")
        }
        
        override fun onServiceDisconnected(arg0: ComponentName) {
            // Perte de la connexion au service
            isBound = false
            _uiState.update { it.copy(isServiceBound = false, isTracking = false) }
            Log.d("StepCounterViewModel", "Service disconnected")
        }
    }
    
    init {
        // Initialisation du service
        bindStepCounterService()
    }
    
    /**
     * Connexion au service de comptage des pas
     */
    private fun bindStepCounterService() {
        try {
            val intent = Intent(context, StepCounterService::class.java)
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
            Log.d("StepCounterViewModel", "Binding to step counter service")
        } catch (e: Exception) {
            Log.e("StepCounterViewModel", "Error binding to step counter service: ${e.message}", e)
            _uiState.update { it.copy(error = e.message) }
        }
    }
    
    /**
     * Démarrage du suivi des pas
     * Active la détection des mouvements avec le gyroscope
     */
    fun startTracking() {
        viewModelScope.launch {
            try {
                stepCounterService?.startTracking()
                _uiState.update { it.copy(isTracking = true) }
                Log.d("StepCounterViewModel", "Step tracking started")
            } catch (e: Exception) {
                Log.e("StepCounterViewModel", "Error starting tracking: ${e.message}", e)
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    /**
     * Arrêt du suivi des pas
     * Désactive la détection des mouvements avec le gyroscope
     */
    fun stopTracking() {
        viewModelScope.launch {
            try {
                stepCounterService?.stopTracking()
                _uiState.update { it.copy(isTracking = false) }
                Log.d("StepCounterViewModel", "Step tracking stopped")
            } catch (e: Exception) {
                Log.e("StepCounterViewModel", "Error stopping tracking: ${e.message}", e)
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    /**
     * Mise à jour du comptage des pas
     * Récupère les valeurs actuelles du service
     */
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
    
    /**
     * Réinitialisation du compteur de pas
     */
    fun resetCounter() {
        viewModelScope.launch {
            try {
                stepCounterService?.resetCounter()
                _uiState.update { it.copy(steps = 0, distance = 0f) }
                Log.d("StepCounterViewModel", "Step counter reset")
            } catch (e: Exception) {
                Log.e("StepCounterViewModel", "Error resetting counter: ${e.message}", e)
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
    
    /**
     * Libération des ressources lors de la destruction du ViewModel
     */
    override fun onCleared() {
        super.onCleared()
        if (isBound) {
            context.unbindService(connection)
            isBound = false
        }
    }
}