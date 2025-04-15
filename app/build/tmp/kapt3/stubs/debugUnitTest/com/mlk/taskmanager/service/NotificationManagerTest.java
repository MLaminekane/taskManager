package com.mlk.taskmanager.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u000eH\u0007J\b\u0010\u0010\u001a\u00020\u000eH\u0007J\b\u0010\u0011\u001a\u00020\u000eH\u0007J\b\u0010\u0012\u001a\u00020\u000eH\u0007J\b\u0010\u0013\u001a\u00020\u000eH\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\n8\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/mlk/taskmanager/service/NotificationManagerTest;", "", "()V", "alarmManager", "Landroid/app/AlarmManager;", "androidNotificationManager", "Landroid/app/NotificationManager;", "context", "Landroid/content/Context;", "locationReminderService", "Lcom/mlk/taskmanager/service/LocationReminderService;", "notificationManager", "Lcom/mlk/taskmanager/service/NotificationManager;", "cancelTaskNotifications should remove geofence", "", "scheduleTaskNotifications should add geofence when location is provided", "scheduleTaskNotifications should not add geofence when location is missing", "scheduleTaskNotifications should schedule time based notifications", "setup", "showTimeNotification should build notification with correct properties", "app_debugUnitTest"})
public final class NotificationManagerTest {
    @org.mockito.Mock()
    private android.content.Context context;
    @org.mockito.Mock()
    private android.app.NotificationManager androidNotificationManager;
    @org.mockito.Mock()
    private android.app.AlarmManager alarmManager;
    @org.mockito.Mock()
    private com.mlk.taskmanager.service.LocationReminderService locationReminderService;
    private com.mlk.taskmanager.service.NotificationManager notificationManager;
    
    public NotificationManagerTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
}