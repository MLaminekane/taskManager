package com.mlk.taskmanager.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Récepteur de diffusion qui gère les notifications basées sur le temps
 * Déclenché par l'AlarmManager pour afficher des notifications avant l'échéance des tâches
 */
@AndroidEntryPoint
class TimeNotificationReceiver : BroadcastReceiver() {
    
    @Inject
    lateinit var notificationManager: NotificationManager
    
    companion object {
        private const val TAG = "TimeNotificationReceiver"
    }
    
    override fun onReceive(context: Context, intent: Intent) {
        val taskId = intent.getLongExtra(NotificationManager.EXTRA_TASK_ID, -1)
        val taskTitle = intent.getStringExtra(NotificationManager.EXTRA_TASK_TITLE) ?: "Tâche"
        val taskDescription = intent.getStringExtra(NotificationManager.EXTRA_TASK_DESCRIPTION) ?: ""
        val notificationId = intent.getIntExtra("notification_id", 0)
        val reminderMinutes = intent.getIntExtra("reminder_minutes", 0)
        
        Log.d(TAG, "Notification temporelle reçue pour la tâche: $taskId ($taskTitle), $reminderMinutes minutes avant échéance")
        
        if (taskId != -1L) {
            notificationManager.showTimeNotification(
                taskId = taskId,
                title = taskTitle,
                description = taskDescription,
                minutesRemaining = reminderMinutes,
                notificationId = notificationId
            )
        }
    }
} 