package com.mlk.taskmanager.service;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lcom/mlk/taskmanager/service/GeofenceBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "locationReminderService", "Lcom/mlk/taskmanager/service/LocationReminderService;", "getLocationReminderService", "()Lcom/mlk/taskmanager/service/LocationReminderService;", "setLocationReminderService", "(Lcom/mlk/taskmanager/service/LocationReminderService;)V", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "getTaskRepository", "()Lcom/mlk/taskmanager/data/repository/TaskRepository;", "setTaskRepository", "(Lcom/mlk/taskmanager/data/repository/TaskRepository;)V", "completeTask", "", "intent", "Landroid/content/Intent;", "handleGeofenceEvent", "onReceive", "context", "Landroid/content/Context;", "snoozeTask", "Companion", "app_debug"})
public final class GeofenceBroadcastReceiver extends android.content.BroadcastReceiver {
    @javax.inject.Inject()
    public com.mlk.taskmanager.service.LocationReminderService locationReminderService;
    @javax.inject.Inject()
    public com.mlk.taskmanager.data.repository.TaskRepository taskRepository;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_GEOFENCE_EVENT = "TaskManager.action.GEOFENCE_EVENT";
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.service.GeofenceBroadcastReceiver.Companion Companion = null;
    
    public GeofenceBroadcastReceiver() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.service.LocationReminderService getLocationReminderService() {
        return null;
    }
    
    public final void setLocationReminderService(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.LocationReminderService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.repository.TaskRepository getTaskRepository() {
        return null;
    }
    
    public final void setTaskRepository(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.TaskRepository p0) {
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void handleGeofenceEvent(android.content.Intent intent) {
    }
    
    private final void completeTask(android.content.Intent intent) {
    }
    
    private final void snoozeTask(android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/mlk/taskmanager/service/GeofenceBroadcastReceiver$Companion;", "", "()V", "ACTION_GEOFENCE_EVENT", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}