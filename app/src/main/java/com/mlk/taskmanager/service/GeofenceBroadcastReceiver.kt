package com.mlk.taskmanager.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GeofenceBroadcastReceiver : BroadcastReceiver() {
    
    @Inject
    lateinit var locationReminderService: LocationReminderService
    
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_GEOFENCE_EVENT) {
            return
        }
        
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        
        if (geofencingEvent?.hasError() == true) {
            val errorMessage = GeofenceStatusCodes
                .getStatusCodeString(geofencingEvent.errorCode)
            return
        }
        
        if (geofencingEvent?.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            val taskId = intent.getLongExtra("taskId", -1)
            val taskTitle = intent.getStringExtra("taskTitle") ?: return
            
            if (taskId != -1L) {
                locationReminderService.showNotification(taskId, taskTitle)
            }
        }
    }
    
    companion object {
        const val ACTION_GEOFENCE_EVENT = "TaskManager.action.GEOFENCE_EVENT"
    }
} 