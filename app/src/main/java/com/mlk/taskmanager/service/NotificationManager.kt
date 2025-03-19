package com.mlk.taskmanager.service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.mlk.taskmanager.R
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Gestionnaire central des notifications de l'application
 * Prend en charge les notifications temporelles et géolocalisées
 */
@Singleton
class NotificationManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val locationReminderService: LocationReminderService
) {
    companion object {
        private const val TAG = "NotificationManager"
        private const val CHANNEL_ID_TIME = "time_notifications"
        private const val CHANNEL_ID_LOCATION = "location_notifications"
        private const val CHANNEL_ID_COMBINED = "context_aware_notifications"
        
        const val ACTION_COMPLETE_TASK = "com.mlk.taskmanager.action.COMPLETE_TASK"
        const val ACTION_SNOOZE_TASK = "com.mlk.taskmanager.action.SNOOZE_TASK"
        const val EXTRA_TASK_ID = "task_id"
        const val EXTRA_TASK_TITLE = "task_title"
        const val EXTRA_TASK_DESCRIPTION = "task_description"
    }
    
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) 
            as NotificationManager
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) 
            as AlarmManager
    
    init {
        createNotificationChannels()
    }
    
    /**
     * Crée les canaux de notification
     */
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Canal pour les notifications basées sur l'heure
            val timeChannel = NotificationChannel(
                CHANNEL_ID_TIME,
                "Notifications temporelles",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications basées sur le temps avant l'échéance des tâches"
                enableLights(true)
                lightColor = Color.BLUE
                enableVibration(true)
            }
            
            // Canal pour les notifications basées sur la localisation
            val locationChannel = NotificationChannel(
                CHANNEL_ID_LOCATION,
                "Notifications de localisation",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications lorsque vous êtes près d'un lieu associé à une tâche"
                enableLights(true)
                lightColor = Color.GREEN
                enableVibration(true)
            }
            
            // Canal pour les notifications contextuelles combinées
            val contextChannel = NotificationChannel(
                CHANNEL_ID_COMBINED,
                "Notifications contextuelles",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications intelligentes basées sur l'heure et la localisation"
                enableLights(true)
                lightColor = Color.MAGENTA
                enableVibration(true)
            }
            
            notificationManager.createNotificationChannel(timeChannel)
            notificationManager.createNotificationChannel(locationChannel)
            notificationManager.createNotificationChannel(contextChannel)
        }
    }
    
    /**
     * Configure toutes les notifications pour une tâche
     * @param task La tâche pour laquelle configurer les notifications
     * @param reminderTimes Liste des intervalles de rappel avant l'échéance (en minutes)
     */
    fun scheduleTaskNotifications(task: Task, reminderTimes: List<Int> = listOf(60, 24*60)) {
        Log.d(TAG, "Programmation des notifications pour la tâche: ${task.id} (${task.title})")
        
        // 1. Configurer les notifications temporelles
        scheduleTimeBasedNotifications(task, reminderTimes)
        
        // 2. Configurer les notifications géolocalisées si applicable
        if (task.latitude != null && task.longitude != null && task.locationRadius != null) {
            locationReminderService.addGeofence(task)
            Log.d(TAG, "Geofence configuré pour la tâche: ${task.id}")
        }
    }
    
    /**
     * Configure des notifications avant l'échéance de la tâche
     * @param task La tâche concernée
     * @param reminderTimes Liste des intervalles de rappel en minutes
     */
    private fun scheduleTimeBasedNotifications(task: Task, reminderTimes: List<Int>) {
        // Ne pas programmer de notifications pour une tâche complétée
        if (task.isCompleted) return
        
        val now = LocalDateTime.now()
        
        // Programmer des notifications pour chaque intervalle de rappel
        for (minutes in reminderTimes) {
            val reminderTime = task.dueDateTime.minus(minutes.toLong(), ChronoUnit.MINUTES)
            
            // Vérifier que le moment de rappel est dans le futur
            if (reminderTime.isAfter(now)) {
                val triggerTimeMillis = reminderTime
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
                
                val notificationId = "${task.id}_${minutes}".hashCode()
                
                // Créer l'intent pour la notification
                val intent = Intent(context, TimeNotificationReceiver::class.java).apply {
                    putExtra(EXTRA_TASK_ID, task.id)
                    putExtra(EXTRA_TASK_TITLE, task.title)
                    putExtra(EXTRA_TASK_DESCRIPTION, task.description)
                    putExtra("notification_id", notificationId)
                    putExtra("reminder_minutes", minutes)
                }
                
                val pendingIntent = PendingIntent.getBroadcast(
                    context, 
                    notificationId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                
                // Programmer l'alarme
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        triggerTimeMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        triggerTimeMillis,
                        pendingIntent
                    )
                }
                
                Log.d(TAG, "Notification temporelle programmée pour ${task.title}: $minutes minutes avant échéance")
            }
        }
    }
    
    /**
     * Affiche une notification temporelle
     */
    fun showTimeNotification(
        taskId: Long,
        title: String,
        description: String,
        minutesRemaining: Int,
        notificationId: Int
    ) {
        // Créer l'intent pour ouvrir l'application
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(EXTRA_TASK_ID, taskId)
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Action compléter
        val completeIntent = Intent(context, GeofenceBroadcastReceiver::class.java).apply {
            action = ACTION_COMPLETE_TASK
            putExtra(EXTRA_TASK_ID, taskId)
        }
        
        val completePendingIntent = PendingIntent.getBroadcast(
            context,
            (notificationId + 1),
            completeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Action reporter
        val snoozeIntent = Intent(context, GeofenceBroadcastReceiver::class.java).apply {
            action = ACTION_SNOOZE_TASK
            putExtra(EXTRA_TASK_ID, taskId)
        }
        
        val snoozePendingIntent = PendingIntent.getBroadcast(
            context,
            (notificationId + 2),
            snoozeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Texte contextuel selon le temps restant
        val timeMessage = when {
            minutesRemaining < 60 -> "$minutesRemaining minutes avant échéance"
            minutesRemaining == 60 -> "1 heure avant échéance"
            minutesRemaining < 24*60 -> "${minutesRemaining / 60} heures avant échéance"
            minutesRemaining == 24*60 -> "1 jour avant échéance"
            else -> "${minutesRemaining / (60*24)} jours avant échéance"
        }
        
        // Construire la notification
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID_TIME)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Rappel: $title")
            .setContentText(timeMessage)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("$timeMessage\n$description"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_menu_save,
                "Terminer",
                completePendingIntent
            )
            .addAction(
                android.R.drawable.ic_menu_recent_history,
                "Reporter",
                snoozePendingIntent
            )
            .setColor(ContextCompat.getColor(context, R.color.purple_500))
        
        // Afficher la notification
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
    
    /**
     * Affiche une notification contextuelle combinant heure et localisation
     */
    fun showContextualNotification(
        taskId: Long,
        title: String,
        description: String,
        notificationId: Int,
        context: ContextType
    ) {
        // Créer l'intent pour ouvrir l'application
        val intent = Intent(this.context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(EXTRA_TASK_ID, taskId)
        }
        
        val pendingIntent = PendingIntent.getActivity(
            this.context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Actions pour compléter ou reporter
        val completeIntent = Intent(this.context, GeofenceBroadcastReceiver::class.java).apply {
            action = ACTION_COMPLETE_TASK
            putExtra(EXTRA_TASK_ID, taskId)
        }
        
        val completePendingIntent = PendingIntent.getBroadcast(
            this.context,
            (notificationId + 1),
            completeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val snoozeIntent = Intent(this.context, GeofenceBroadcastReceiver::class.java).apply {
            action = ACTION_SNOOZE_TASK
            putExtra(EXTRA_TASK_ID, taskId)
        }
        
        val snoozePendingIntent = PendingIntent.getBroadcast(
            this.context,
            (notificationId + 2),
            snoozeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Message contextuel selon le type de contexte
        val contextMessage = when (context) {
            ContextType.LOCATION_ONLY -> "Vous êtes à proximité du lieu de cette tâche"
            ContextType.TIME_ONLY -> "Cette tâche arrive bientôt à échéance"
            ContextType.LOCATION_AND_TIME -> "Vous êtes à proximité et cette tâche est bientôt à échéance!"
        }
        
        // Construire la notification contextuelle
        val notificationBuilder = NotificationCompat.Builder(this.context, CHANNEL_ID_COMBINED)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("$title - Action Recommandée")
            .setContentText(contextMessage)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("$contextMessage\n$description"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_menu_save,
                "Terminer",
                completePendingIntent
            )
            .addAction(
                android.R.drawable.ic_menu_recent_history,
                "Reporter",
                snoozePendingIntent
            )
            .setColor(ContextCompat.getColor(this.context, R.color.purple_500))
        
        // Afficher la notification
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
    
    /**
     * Annule toutes les notifications programmées pour une tâche
     */
    fun cancelTaskNotifications(taskId: Long) {
        // Annuler les notifications géolocalisées
        locationReminderService.removeGeofence(taskId)
        
        // Pour les notifications temporelles, on ne peut pas directement les annuler
        // mais on peut mettre à jour l'UI pour ignorer les notifications futures
        Log.d(TAG, "Toutes les notifications annulées pour la tâche $taskId")
    }
    
    /**
     * Enum pour indiquer le type de contexte d'une notification
     */
    enum class ContextType {
        LOCATION_ONLY,
        TIME_ONLY,
        LOCATION_AND_TIME
    }
} 