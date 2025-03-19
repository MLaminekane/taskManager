package com.mlk.taskmanager.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import com.mlk.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class GeofenceBroadcastReceiver : BroadcastReceiver() {
    
    @Inject
    lateinit var locationReminderService: LocationReminderService
    
    @Inject
    lateinit var taskRepository: TaskRepository
    
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ACTION_GEOFENCE_EVENT -> handleGeofenceEvent(intent)
            "TaskManager.action.COMPLETE_TASK" -> completeTask(intent)
            "TaskManager.action.SNOOZE_TASK" -> snoozeTask(intent)
        }
    }
    
    private fun handleGeofenceEvent(intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        
        if (geofencingEvent?.hasError() == true) {
            val errorMessage = GeofenceStatusCodes
                .getStatusCodeString(geofencingEvent.errorCode)
            return
        }
        
        if (geofencingEvent?.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            val taskId = intent.getLongExtra("taskId", -1)
            val taskTitle = intent.getStringExtra("taskTitle") ?: return
            val taskDescription = intent.getStringExtra("taskDescription")
            
            if (taskId != -1L) {
                locationReminderService.showNotification(taskId, taskTitle, taskDescription)
            }
        }
    }
    
    private fun completeTask(intent: Intent) {
        val taskId = intent.getLongExtra("taskId", -1)
        if (taskId != -1L) {
            coroutineScope.launch {
                val task = taskRepository.getTaskById(taskId)
                task?.let {
                    // Marquer la tâche comme complétée
                    val completedTask = it.copy(isCompleted = true)
                    taskRepository.updateTask(completedTask)
                    
                    // Supprimer le geofence car la tâche est complétée
                    locationReminderService.removeGeofence(taskId)
                }
            }
        }
    }
    
    private fun snoozeTask(intent: Intent) {
        val taskId = intent.getLongExtra("taskId", -1)
        if (taskId != -1L) {
            coroutineScope.launch {
                val task = taskRepository.getTaskById(taskId)
                task?.let {
                    // Reporter la tâche d'une heure (ou selon une autre logique)
                    val dueDateTime = LocalDateTime.now().plusHours(1)
                    val updatedTask = it.copy(dueDateTime = dueDateTime)
                    taskRepository.updateTask(updatedTask)
                }
            }
        }
    }
    
    companion object {
        const val ACTION_GEOFENCE_EVENT = "TaskManager.action.GEOFENCE_EVENT"
    }
} 