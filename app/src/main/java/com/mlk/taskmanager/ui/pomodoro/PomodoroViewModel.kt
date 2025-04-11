package com.mlk.taskmanager.ui.pomodoro

import android.app.ActivityManager
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * État de l'interface utilisateur pour le mode Pomodoro
 * @param minutes Minutes restantes dans le timer
 * @param seconds Secondes restantes dans le timer
 * @param isRunning Indique si le timer est en cours d'exécution
 * @param isBreak Indique si c'est une période de pause (true) ou de travail (false)
 * @param dndEnabled Mode "Ne pas déranger" activé
 * @param completedSessions Nombre de sessions de travail terminées
 * @param totalFocusMinutes Nombre total de minutes de concentration
 * @param focusRate Taux de concentration (pourcentage)
 * @param showPermissionDialog Indique si une demande de permission est nécessaire
 * @param error Message d'erreur éventuel
 */
data class PomodoroUiState(
    val minutes: Int = 25,
    val seconds: Int = 0,
    val isRunning: Boolean = false,
    val isBreak: Boolean = false,
    val dndEnabled: Boolean = false,
    val notificationBlocked: Boolean = false,
    val completedSessions: Int = 0,
    val totalFocusMinutes: Int = 0,
    val focusRate: Int = 0,
    val showPermissionDialog: Boolean = false,
    val error: String? = null
)

/**
 * ViewModel pour la technique Pomodoro
 * Gère le minuteur, les périodes de travail/pause et les statistiques
 */
@HiltViewModel
class PomodoroViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: com.mlk.taskmanager.service.NotificationManager,
    private val androidNotificationManager: NotificationManager
) : ViewModel() {

    companion object {
        private const val TAG = "PomodoroViewModel"
        private const val FOCUS_DURATION_MINUTES = 25
        private const val BREAK_DURATION_MINUTES = 5
    }

    // État de l'interface utilisateur
    private val _uiState = MutableStateFlow(PomodoroUiState())
    val uiState: StateFlow<PomodoroUiState> = _uiState

    // Timer pour le compte à rebours
    private var timer: CountDownTimer? = null
    
    // Gestionnaire audio pour contrôler le son du téléphone
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    
    // Gestionnaire de notification compact
    private val notificationManagerCompat = NotificationManagerCompat.from(context)
    
    // Sauvegarde du mode audio précédent
    private var previousRingerMode = AudioManager.RINGER_MODE_NORMAL
    private var previousInterruptionFilter = NotificationManager.INTERRUPTION_FILTER_ALL

    init {
        // Vérifier si l'application a la permission de modifier le mode Ne pas déranger
        checkNotificationPolicyAccess()
    }

    /**
     * Vérifie si l'application a l'autorisation de modifier la politique de notification
     * et met à jour l'état en conséquence
     */
    private fun checkNotificationPolicyAccess() {
        val hasPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            androidNotificationManager.isNotificationPolicyAccessGranted
        } else {
            true
        }
        
        // Sauvegarder le mode actuel du téléphone
        previousRingerMode = audioManager.ringerMode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            previousInterruptionFilter = androidNotificationManager.currentInterruptionFilter
        }
        
        _uiState.update { it.copy(
            dndEnabled = it.dndEnabled && hasPermission,
            showPermissionDialog = !hasPermission && it.dndEnabled
        )}
        
        Log.d(TAG, "Notification policy access granted: $hasPermission")
    }

    /**
     * Ouvre les paramètres système pour demander l'accès à la politique de notification
     * Retourne un Intent que l'activité doit démarrer
     */
    fun getNotificationPolicyAccessIntent(): Intent? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        } else {
            null
        }
    }
    
    /**
     * Active le mode Ne pas déranger du système
     * Utilise toutes les approches possibles selon la version d'Android
     */
    private fun enableDoNotDisturb() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        
        try {
            // Vérifier la permission
            if (!androidNotificationManager.isNotificationPolicyAccessGranted) {
                Log.e(TAG, "Cannot enable DND: permission not granted")
                return
            }
            
            // Sauvegarder l'état actuel
            previousRingerMode = audioManager.ringerMode
            previousInterruptionFilter = androidNotificationManager.currentInterruptionFilter
            
            Log.d(TAG, "Enabling DND mode with multiple approaches")
            
            // 1. Méthode principale - API NotificationManager
            androidNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
            Log.d(TAG, "Set interruption filter to PRIORITY")
            
            // 2. Méthode alternative - Contrôle du volume
            try {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_NOTIFICATION,
                    AudioManager.ADJUST_MUTE,
                    0
                )
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_RING,
                    AudioManager.ADJUST_MUTE,
                    0
                )
                Log.d(TAG, "Muted notification and ring streams")
            } catch (e: Exception) {
                Log.e(TAG, "Error adjusting stream volume: ${e.message}")
            }
            
            // 3. Tentative directe de changer le mode sonnerie
            try {
                audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
                Log.d(TAG, "Set ringer mode to SILENT")
            } catch (e: Exception) {
                Log.e(TAG, "Error setting ringer mode: ${e.message}")
            }
            
            // 4. Méthode supplémentaire pour bloquer les notifications au niveau app
            notificationManager.pauseNonEssentialNotifications()
            
        } catch (e: Exception) {
            Log.e(TAG, "Error enabling DND mode: ${e.message}", e)
        }
    }
    
    /**
     * Désactive le mode Ne pas déranger et restaure les paramètres précédents
     */
    private fun disableDoNotDisturb() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        
        try {
            // Vérifier la permission
            if (!androidNotificationManager.isNotificationPolicyAccessGranted) {
                return
            }
            
            Log.d(TAG, "Disabling DND mode and restoring previous settings")
            
            // 1. Restaurer le filtre d'interruption
            androidNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
            Log.d(TAG, "Restored interruption filter to ALL")
            
            // 2. Réactiver les flux audio
            try {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_NOTIFICATION,
                    AudioManager.ADJUST_UNMUTE,
                    0
                )
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_RING,
                    AudioManager.ADJUST_UNMUTE,
                    0
                )
                Log.d(TAG, "Unmuted notification and ring streams")
            } catch (e: Exception) {
                Log.e(TAG, "Error unmuting streams: ${e.message}")
            }
            
            // 3. Restaurer le mode sonnerie précédent
            try {
                if (previousRingerMode != audioManager.ringerMode) {
                    audioManager.ringerMode = previousRingerMode
                    Log.d(TAG, "Restored previous ringer mode: $previousRingerMode")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error restoring ringer mode: ${e.message}")
            }
            
            // 4. Réactiver les notifications au niveau app
            notificationManager.resumeNonEssentialNotifications()
            
        } catch (e: Exception) {
            Log.e(TAG, "Error disabling DND mode: ${e.message}", e)
        }
    }

    /**
     * Démarre le compte à rebours pour une durée donnée
     * @param minutes Durée en minutes du compte à rebours
     */
    private fun startCountdown(minutes: Int) {
        try {
            // Annule le timer existant s'il y en a un
            timer?.cancel()
            
            val totalMillis = minutes * 60 * 1000L
            
            timer = object : CountDownTimer(totalMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val minutesRemaining = (millisUntilFinished / 1000 / 60).toInt()
                    val secondsRemaining = ((millisUntilFinished / 1000) % 60).toInt()
                    
                    // Mise à jour de l'état avec le temps restant
                    _uiState.update { it.copy(
                        minutes = minutesRemaining,
                        seconds = secondsRemaining
                    ) }
                }

                override fun onFinish() {
                    try {
                        if (!_uiState.value.isBreak) {
                            // Session de travail terminée, passage à une pause
                            val newSessions = _uiState.value.completedSessions + 1
                            val newTotalMinutes = _uiState.value.totalFocusMinutes + FOCUS_DURATION_MINUTES
                            
                            _uiState.update { it.copy(
                                completedSessions = newSessions,
                                totalFocusMinutes = newTotalMinutes,
                                focusRate = calculateFocusRate(newSessions, newTotalMinutes),
                                isBreak = true,
                                minutes = BREAK_DURATION_MINUTES,
                                seconds = 0,
                                isRunning = false
                            ) }
                            
                            Log.d(TAG, "Focus session completed. Starting break.")
                        } else {
                            // Pause terminée, retour à une session de travail
                            _uiState.update { it.copy(
                                isBreak = false,
                                minutes = FOCUS_DURATION_MINUTES,
                                seconds = 0,
                                isRunning = false
                            ) }
                            
                            Log.d(TAG, "Break completed. Ready for next focus session.")
                        }
                        
                        // Mise à jour des paramètres de notification
                        updateNotificationSettings()
                    } catch (e: Exception) {
                        Log.e(TAG, "Error in onFinish: ${e.message}", e)
                        _uiState.update { it.copy(error = "Erreur lors de la fin du minuteur: ${e.message}") }
                    }
                }
            }.start()

            // Mise à jour de l'état pour indiquer que le timer est en cours
            _uiState.update { it.copy(isRunning = true) }
            updateNotificationSettings()
            
            Log.d(TAG, "Started countdown for $minutes minutes")
        } catch (e: Exception) {
            Log.e(TAG, "Error starting countdown: ${e.message}", e)
            _uiState.update { 
                it.copy(
                    isRunning = false,
                    error = "Erreur lors du démarrage du minuteur: ${e.message}"
                ) 
            }
        }
    }

    /**
     * Calcule le taux de concentration en pourcentage
     * @param sessions Nombre de sessions complétées
     * @param totalMinutes Nombre total de minutes de concentration
     * @return Taux de concentration en pourcentage
     */
    private fun calculateFocusRate(sessions: Int, totalMinutes: Int): Int {
        return try {
            if (sessions == 0) {
                0
            } else {
                val expectedMinutes = sessions * FOCUS_DURATION_MINUTES
                ((totalMinutes.toFloat() / expectedMinutes.toFloat()) * 100).toInt().coerceIn(0, 100)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error calculating focus rate: ${e.message}", e)
            0
        }
    }

    /**
     * Démarre le minuteur
     */
    fun startTimer() {
        try {
            Log.d(TAG, "Starting timer")
            startCountdown(_uiState.value.minutes)
        } catch (e: Exception) {
            Log.e(TAG, "Error in startTimer: ${e.message}", e)
            _uiState.update { it.copy(error = "Erreur lors du démarrage: ${e.message}") }
        }
    }

    /**
     * Met en pause le minuteur
     */
    fun pauseTimer() {
        try {
            Log.d(TAG, "Pausing timer")
            timer?.cancel()
            _uiState.update { it.copy(isRunning = false) }
            updateNotificationSettings()
        } catch (e: Exception) {
            Log.e(TAG, "Error in pauseTimer: ${e.message}", e)
            _uiState.update { it.copy(error = "Erreur lors de la pause: ${e.message}") }
        }
    }

    /**
     * Réinitialise le minuteur à la durée par défaut
     */
    fun resetTimer() {
        try {
            Log.d(TAG, "Resetting timer")
            timer?.cancel()
            
            // Réinitialisation à 25 min pour le travail ou 5 min pour la pause
            val defaultMinutes = if (_uiState.value.isBreak) BREAK_DURATION_MINUTES else FOCUS_DURATION_MINUTES
            
            _uiState.update { it.copy(
                minutes = defaultMinutes,
                seconds = 0,
                isRunning = false
            ) }
            
            updateNotificationSettings()
        } catch (e: Exception) {
            Log.e(TAG, "Error in resetTimer: ${e.message}", e)
            _uiState.update { it.copy(error = "Erreur lors de la réinitialisation: ${e.message}") }
        }
    }

    /**
     * Passe à la session suivante (de travail à pause ou inversement)
     */
    fun skipSession() {
        try {
            Log.d(TAG, "Skipping current session")
            timer?.cancel()
            
            if (_uiState.value.isBreak) {
                // Passer de la pause au travail
                _uiState.update { it.copy(
                    isBreak = false,
                    minutes = FOCUS_DURATION_MINUTES,
                    seconds = 0,
                    isRunning = false
                ) }
            } else {
                // Passer du travail à la pause et comptabiliser le temps passé
                val minutesWorked = (FOCUS_DURATION_MINUTES - _uiState.value.minutes) + (_uiState.value.seconds / 60)
                
                _uiState.update { it.copy(
                    isBreak = true,
                    minutes = BREAK_DURATION_MINUTES,
                    seconds = 0,
                    isRunning = false,
                    completedSessions = it.completedSessions + 1,
                    totalFocusMinutes = it.totalFocusMinutes + minutesWorked
                ) }
            }
            
            updateNotificationSettings()
        } catch (e: Exception) {
            Log.e(TAG, "Error in skipSession: ${e.message}", e)
            _uiState.update { it.copy(error = "Erreur lors du changement de session: ${e.message}") }
        }
    }

    /**
     * Active/désactive le mode "Ne pas déranger"
     * Si la permission n'est pas accordée, affiche une demande de permission
     */
    fun toggleDnd() {
        try {
            Log.d(TAG, "Toggling DND mode")
            
            // Si on active le DND, vérifier d'abord la permission
            val newDndState = !_uiState.value.dndEnabled
            
            if (newDndState) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && 
                    !androidNotificationManager.isNotificationPolicyAccessGranted) {
                    // Demander la permission si on active le mode DND
                    _uiState.update { it.copy(
                        showPermissionDialog = true,
                        error = "Permission requise pour le mode Ne pas déranger"
                    ) }
                    return
                }
            }
            
            // Si on a la permission ou qu'on désactive, mettre à jour
            _uiState.update { it.copy(
                dndEnabled = newDndState,
                showPermissionDialog = false
            ) }
            
            // Appliquer immédiatement le changement
            if (newDndState) {
                enableDoNotDisturb()
            } else {
                disableDoNotDisturb()
            }
            
            // Mettre à jour avec les contraintes du timer
            updateNotificationSettings()
            
        } catch (e: Exception) {
            Log.e(TAG, "Error toggling DND: ${e.message}", e)
            _uiState.update { it.copy(error = "Erreur lors du changement de mode DND: ${e.message}") }
        }
    }

    /**
     * Indique que l'utilisateur a été informé de la nécessité d'une permission
     */
    fun permissionDialogShown() {
        _uiState.update { it.copy(showPermissionDialog = false) }
    }

    /**
     * Active/désactive le blocage des notifications
     */
    fun toggleNotificationBlocking() {
        try {
            Log.d(TAG, "Toggling notification blocking")
            _uiState.update { it.copy(notificationBlocked = !it.notificationBlocked) }
            
            // Appliquer immédiatement le changement si le timer est actif
            if (_uiState.value.isRunning && !_uiState.value.isBreak) {
                updateNotificationSettings()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error toggling notification blocking: ${e.message}", e)
            _uiState.update { it.copy(error = "Erreur lors du changement de blocage des notifications: ${e.message}") }
        }
    }

    /**
     * Met à jour les paramètres de notification en fonction de l'état actuel
     */
    private fun updateNotificationSettings() {
        try {
            Log.d(TAG, "Updating notification settings. Running: ${_uiState.value.isRunning}, Break: ${_uiState.value.isBreak}, DND: ${_uiState.value.dndEnabled}")
            
            // Si le mode DND est activé par l'utilisateur, le maintenir
            // OU si en session focus (timer actif et pas en pause)
            if (_uiState.value.dndEnabled || (_uiState.value.isRunning && !_uiState.value.isBreak)) {
                if (_uiState.value.dndEnabled) {
                    enableDoNotDisturb()
                }
            } else {
                // Désactiver le mode DND uniquement s'il n'est pas activé par l'utilisateur
                disableDoNotDisturb()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error updating notification settings: ${e.message}", e)
            _uiState.update { it.copy(error = "Erreur lors de la mise à jour des notifications: ${e.message}") }
        }
    }

    /**
     * Nettoyage lors de la destruction du ViewModel
     */
    override fun onCleared() {
        try {
            super.onCleared()
            Log.d(TAG, "ViewModel being cleared, resetting all notification settings")
            
            // Annuler le timer en cours
            timer?.cancel()
            
            // Restaurer les paramètres de notification à la sortie
            disableDoNotDisturb()
            
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCleared: ${e.message}", e)
        }
    }
}