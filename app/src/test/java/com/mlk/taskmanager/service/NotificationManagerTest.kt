package com.mlk.taskmanager.service

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class NotificationManagerTest {
    
    @Mock
    private lateinit var context: Context
    
    @Mock
    private lateinit var androidNotificationManager: NotificationManager
    
    @Mock
    private lateinit var alarmManager: AlarmManager
    
    @Mock
    private lateinit var locationReminderService: LocationReminderService
    
    private lateinit var notificationManager: com.mlk.taskmanager.service.NotificationManager
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        // Mock context to return system services
        `when`(context.getSystemService(Context.NOTIFICATION_SERVICE)).thenReturn(androidNotificationManager)
        `when`(context.getSystemService(Context.ALARM_SERVICE)).thenReturn(alarmManager)
        
        // Create instance to test
        notificationManager = com.mlk.taskmanager.service.NotificationManager(context, locationReminderService)
    }
    
    @Test
    fun `scheduleTaskNotifications should schedule time based notifications`() {
        // Given
        val task = Task(
            id = 1,
            title = "Test Task",
            description = "Description",
            dueDateTime = LocalDateTime.now().plusHours(3), // 3 hours in the future
            priority = Priority.HIGH
        )
        
        // When
        notificationManager.scheduleTaskNotifications(task)
        
        // Then - Verify expected calls to Android AlarmManager
        // It's hard to verify exact PendingIntents, but we can verify setExact or setExactAndAllowWhileIdle is called
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            verify(alarmManager, atLeastOnce()).setExactAndAllowWhileIdle(anyInt(), anyLong(), any())
        } else {
            verify(alarmManager, atLeastOnce()).setExact(anyInt(), anyLong(), any())
        }
    }
    
    @Test
    fun `scheduleTaskNotifications should add geofence when location is provided`() {
        // Given
        val task = Task(
            id = 1,
            title = "Location Test Task",
            description = "Description",
            dueDateTime = LocalDateTime.now().plusHours(3),
            priority = Priority.HIGH,
            latitude = 48.8566,
            longitude = 2.3522,
            locationRadius = 200f
        )
        
        doNothing().`when`(locationReminderService).addGeofence(any())
        
        // When
        notificationManager.scheduleTaskNotifications(task)
        
        // Then
        verify(locationReminderService).addGeofence(task)
    }
    
    @Test
    fun `scheduleTaskNotifications should not add geofence when location is missing`() {
        // Given
        val task = Task(
            id = 1,
            title = "Test Task Without Location",
            description = "Description",
            dueDateTime = LocalDateTime.now().plusHours(3),
            priority = Priority.HIGH
            // No geographic coordinates
        )
        
        // When
        notificationManager.scheduleTaskNotifications(task)
        
        // Then
        verify(locationReminderService, never()).addGeofence(any())
    }
    
    @Test
    fun `showTimeNotification should build notification with correct properties`() {
        // Given
        val taskId = 1L
        val title = "Test Title"
        val description = "Test Description"
        val minutesRemaining = 60 // 1 hour
        val notificationId = 123
        
        // When
        notificationManager.showTimeNotification(
            taskId = taskId,
            title = title,
            description = description,
            minutesRemaining = minutesRemaining,
            notificationId = notificationId
        )
        
        // Then
        verify(androidNotificationManager).notify(eq(notificationId), any())
    }
    
    @Test
    fun `cancelTaskNotifications should remove geofence`() {
        // Given
        val taskId = 1L
        
        doNothing().`when`(locationReminderService).removeGeofence(anyLong())
        
        // When
        notificationManager.cancelTaskNotifications(taskId)
        
        // Then
        verify(locationReminderService).removeGeofence(taskId)
    }
} 