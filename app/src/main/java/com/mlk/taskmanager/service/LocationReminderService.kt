package com.mlk.taskmanager.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import android.graphics.Color
import androidx.core.app.NotificationCompat.BigTextStyle
import com.mlk.taskmanager.R

@Singleton
class LocationReminderService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: NotificationManager
) {
    private val geofencingClient: GeofencingClient = LocationServices.getGeofencingClient(context)
    
    init {
        createNotificationChannel()
    }
    
    fun addGeofence(task: Task) {
        if (task.latitude == null || task.longitude == null || task.locationRadius == null) {
            return
        }
        
        val geofence = Geofence.Builder()
            .setRequestId(task.id.toString())
            .setCircularRegion(
                task.latitude,
                task.longitude,
                task.locationRadius
            )
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build()
        
        val request = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence)
            .build()
        
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java).apply {
            action = GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT
            putExtra("taskId", task.id)
            putExtra("taskTitle", task.title)
            putExtra("taskDescription", task.description)
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        
        try {
            geofencingClient.addGeofences(request, pendingIntent)
        } catch (e: SecurityException) {
            // Handle permission not granted
        }
    }
    
    fun removeGeofence(taskId: Long) {
        geofencingClient.removeGeofences(listOf(taskId.toString()))
    }
    
    fun showNotification(taskId: Long, title: String, description: String? = null) {
        // Intent pour ouvrir l'application
        val contentIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("taskId", taskId)
        }
        
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            taskId.toInt(),
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Intent pour marquer comme complété
        val completeIntent = Intent(context, GeofenceBroadcastReceiver::class.java).apply {
            action = "TaskManager.action.COMPLETE_TASK"
            putExtra("taskId", taskId)
        }
        
        val completePendingIntent = PendingIntent.getBroadcast(
            context,
            (taskId + 1000).toInt(), // Différent ID pour éviter les collisions
            completeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Intent pour snoozer
        val snoozeIntent = Intent(context, GeofenceBroadcastReceiver::class.java).apply {
            action = "TaskManager.action.SNOOZE_TASK"
            putExtra("taskId", taskId)
        }
        
        val snoozePendingIntent = PendingIntent.getBroadcast(
            context,
            (taskId + 2000).toInt(), // Différent ID pour éviter les collisions
            snoozeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Using system icon for now
            .setContentTitle("Location Reminder")
            .setContentText("You're near the location for: $title")
            .setStyle(BigTextStyle()
                .bigText("You're near the location for: $title\n${description ?: ""}")
                .setBigContentTitle("Location Reminder")
                .setSummaryText("Task Manager")
            )
            .setColor(Color.parseColor("#613BE7"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(contentPendingIntent)
            .addAction(
                android.R.drawable.ic_menu_save, // Using system icon for now
                "Complete", 
                completePendingIntent
            )
            .addAction(
                android.R.drawable.ic_menu_recent_history, // Using system icon for now
                "Snooze", 
                snoozePendingIntent
            )
            .build()
        
        notificationManager.notify(taskId.toInt(), notification)
    }
    
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Location Reminders",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications for location-based task reminders"
            enableLights(true)
            lightColor = Color.parseColor("#613BE7")
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)
    }
    
    companion object {
        const val CHANNEL_ID = "location_reminders"
    }
} 