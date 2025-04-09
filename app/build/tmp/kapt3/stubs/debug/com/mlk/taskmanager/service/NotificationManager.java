package com.mlk.taskmanager.service;

/**
 * Gestionnaire central des notifications de l'application
 * Prend en charge les notifications temporelles et géolocalisées
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 *2\u00020\u0001:\u0002*+B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fJ\b\u0010\u0013\u001a\u00020\u0011H\u0002J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J&\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cJ\u001e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J\u001e\u0010\"\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\b\u0010#\u001a\u00020\nH\u0002J.\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020\'J.\u0010(\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020!2\u0006\u0010&\u001a\u00020!R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/mlk/taskmanager/service/NotificationManager;", "", "context", "Landroid/content/Context;", "locationReminderService", "Lcom/mlk/taskmanager/service/LocationReminderService;", "(Landroid/content/Context;Lcom/mlk/taskmanager/service/LocationReminderService;)V", "alarmManager", "Landroid/app/AlarmManager;", "isNotificationsPaused", "", "notificationManager", "Landroid/app/NotificationManager;", "pausedNotifications", "", "", "cancelTaskNotifications", "", "taskId", "createNotificationChannels", "pauseNonEssentialNotifications", "resumeNonEssentialNotifications", "scheduleSingleNotification", "task", "Lcom/mlk/taskmanager/data/model/Task;", "notificationTime", "Ljava/time/LocalDateTime;", "title", "", "message", "scheduleTaskNotifications", "reminderTimes", "", "", "scheduleTimeBasedNotifications", "shouldShowNotification", "showContextualNotification", "description", "notificationId", "Lcom/mlk/taskmanager/service/NotificationManager$ContextType;", "showTimeNotification", "minutesRemaining", "Companion", "ContextType", "app_debug"})
public final class NotificationManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.LocationReminderService locationReminderService = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "NotificationManager";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID_TIME = "time_notifications";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID_LOCATION = "location_notifications";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID_COMBINED = "context_aware_notifications";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_COMPLETE_TASK = "com.mlk.taskmanager.action.COMPLETE_TASK";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SNOOZE_TASK = "com.mlk.taskmanager.action.SNOOZE_TASK";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TASK_ID = "task_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TASK_TITLE = "task_title";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TASK_DESCRIPTION = "task_description";
    @org.jetbrains.annotations.NotNull()
    private final android.app.NotificationManager notificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private final android.app.AlarmManager alarmManager = null;
    private boolean isNotificationsPaused = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Long> pausedNotifications = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.service.NotificationManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public NotificationManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.LocationReminderService locationReminderService) {
        super();
    }
    
    /**
     * Crée les canaux de notification
     */
    private final void createNotificationChannels() {
    }
    
    /**
     * Configure toutes les notifications pour une tâche
     * @param task La tâche pour laquelle configurer les notifications
     * @param reminderTimes Liste des intervalles de rappel avant l'échéance (en minutes)
     */
    public final void scheduleTaskNotifications(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> reminderTimes) {
    }
    
    /**
     * Configure des notifications avant l'échéance de la tâche
     * @param task La tâche concernée
     * @param reminderTimes Liste des intervalles de rappel en minutes
     */
    private final void scheduleTimeBasedNotifications(com.mlk.taskmanager.data.model.Task task, java.util.List<java.lang.Integer> reminderTimes) {
    }
    
    /**
     * Affiche une notification temporelle
     */
    public final void showTimeNotification(long taskId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, int minutesRemaining, int notificationId) {
    }
    
    /**
     * Affiche une notification contextuelle combinant heure et localisation
     */
    public final void showContextualNotification(long taskId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, int notificationId, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.NotificationManager.ContextType context) {
    }
    
    /**
     * Annule toutes les notifications programmées pour une tâche
     */
    public final void cancelTaskNotifications(long taskId) {
    }
    
    public final void pauseNonEssentialNotifications() {
    }
    
    public final void resumeNonEssentialNotifications() {
    }
    
    private final boolean shouldShowNotification() {
        return false;
    }
    
    /**
     * Planifie une notification unique pour une tâche
     * @param task La tâche concernée
     * @param notificationTime Le moment où la notification doit être affichée
     * @param title Le titre de la notification
     * @param message Le message de la notification
     */
    public final void scheduleSingleNotification(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime notificationTime, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/mlk/taskmanager/service/NotificationManager$Companion;", "", "()V", "ACTION_COMPLETE_TASK", "", "ACTION_SNOOZE_TASK", "CHANNEL_ID_COMBINED", "CHANNEL_ID_LOCATION", "CHANNEL_ID_TIME", "EXTRA_TASK_DESCRIPTION", "EXTRA_TASK_ID", "EXTRA_TASK_TITLE", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    /**
     * Enum pour indiquer le type de contexte d'une notification
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/mlk/taskmanager/service/NotificationManager$ContextType;", "", "(Ljava/lang/String;I)V", "LOCATION_ONLY", "TIME_ONLY", "LOCATION_AND_TIME", "app_debug"})
    public static enum ContextType {
        /*public static final*/ LOCATION_ONLY /* = new LOCATION_ONLY() */,
        /*public static final*/ TIME_ONLY /* = new TIME_ONLY() */,
        /*public static final*/ LOCATION_AND_TIME /* = new LOCATION_AND_TIME() */;
        
        ContextType() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.mlk.taskmanager.service.NotificationManager.ContextType> getEntries() {
            return null;
        }
    }
}