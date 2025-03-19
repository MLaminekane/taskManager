package com.mlk.taskmanager.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.mlk.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Récepteur de diffusion qui est déclenché lors du redémarrage de l'appareil
 * Restaure les alarmes et les géofences pour toutes les tâches actives
 */
@AndroidEntryPoint
class BootCompletedReceiver : BroadcastReceiver() {
    
    @Inject
    lateinit var taskRepository: TaskRepository
    
    @Inject
    lateinit var notificationManager: NotificationManager
    
    companion object {
        private const val TAG = "BootCompletedReceiver"
    }
    
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d(TAG, "Appareil redémarré, restauration des notifications")
            
            // Utiliser un scope de coroutine pour les opérations asynchrones
            val scope = CoroutineScope(Dispatchers.IO)
            
            scope.launch {
                try {
                    // Récupérer toutes les tâches non complétées
                    val tasks = taskRepository.getAllTasksSync().filter { !it.isCompleted }
                    
                    Log.d(TAG, "Restauration des notifications pour ${tasks.size} tâches")
                    
                    // Reprogrammer les notifications pour chaque tâche
                    tasks.forEach { task ->
                        notificationManager.scheduleTaskNotifications(task)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Erreur lors de la restauration des notifications: ${e.message}")
                }
            }
        }
    }
} 