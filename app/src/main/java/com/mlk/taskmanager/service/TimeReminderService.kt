package com.mlk.taskmanager.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeReminderService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val workManager: WorkManager,
    private val notificationManager: NotificationManager
) {
    init {
        createNotificationChannel()
    }
    
    fun scheduleReminder(task: Task) {
        val now = LocalDateTime.now()
        if (task.dueDateTime.isBefore(now)) {
            return
        }
        
        val delay = Duration.between(now, task.dueDateTime)
        
        val reminderWork = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(delay.toMillis(), TimeUnit.MILLISECONDS)
            .setInputData(workDataOf(
                "taskId" to task.id,
                "taskTitle" to task.title
            ))
            .build()
        
        workManager.enqueueUniqueWork(
            "reminder_${task.id}",
            ExistingWorkPolicy.REPLACE,
            reminderWork
        )
    }
    
    fun cancelReminder(taskId: Long) {
        workManager.cancelUniqueWork("reminder_${taskId}")
    }
    
    fun showNotification(taskId: Long, title: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            taskId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Task Due")
            .setContentText(title)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        
        notificationManager.notify(taskId.toInt(), notification)
    }
    
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Time Reminders",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications for time-based task reminders"
        }
        notificationManager.createNotificationChannel(channel)
    }
    
    companion object {
        const val CHANNEL_ID = "time_reminders"
    }
}

class ReminderWorker(
    context: Context,
    params: WorkerParameters,
    private val timeReminderService: TimeReminderService
) : Worker(context, params) {
    
    override fun doWork(): Result {
        val taskId = inputData.getLong("taskId", -1)
        val taskTitle = inputData.getString("taskTitle") ?: return Result.failure()
        
        if (taskId != -1L) {
            timeReminderService.showNotification(taskId, taskTitle)
        }
        
        return Result.success()
    }
    
    class Factory @Inject constructor(
        private val timeReminderService: TimeReminderService
    ) : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker? {
            return when (workerClassName) {
                ReminderWorker::class.java.name ->
                    ReminderWorker(appContext, workerParameters, timeReminderService)
                else -> null
            }
        }
    }
} 