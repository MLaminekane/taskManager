package com.mlk.taskmanager.service;

/**
 * Récepteur de diffusion qui est déclenché lors du redémarrage de l'appareil
 * Restaure les alarmes et les géofences pour toutes les tâches actives
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0016"}, d2 = {"Lcom/mlk/taskmanager/service/BootCompletedReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "notificationManager", "Lcom/mlk/taskmanager/service/NotificationManager;", "getNotificationManager", "()Lcom/mlk/taskmanager/service/NotificationManager;", "setNotificationManager", "(Lcom/mlk/taskmanager/service/NotificationManager;)V", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "getTaskRepository", "()Lcom/mlk/taskmanager/data/repository/TaskRepository;", "setTaskRepository", "(Lcom/mlk/taskmanager/data/repository/TaskRepository;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_debug"})
public final class BootCompletedReceiver extends android.content.BroadcastReceiver {
    @javax.inject.Inject()
    public com.mlk.taskmanager.data.repository.TaskRepository taskRepository;
    @javax.inject.Inject()
    public com.mlk.taskmanager.service.NotificationManager notificationManager;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "BootCompletedReceiver";
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.service.BootCompletedReceiver.Companion Companion = null;
    
    public BootCompletedReceiver() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.repository.TaskRepository getTaskRepository() {
        return null;
    }
    
    public final void setTaskRepository(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.TaskRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.service.NotificationManager getNotificationManager() {
        return null;
    }
    
    public final void setNotificationManager(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.NotificationManager p0) {
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/mlk/taskmanager/service/BootCompletedReceiver$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}