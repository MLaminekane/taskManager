package com.mlk.taskmanager.service;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u0011J\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0010H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010 J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0010H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\"\u0010 J$\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b$\u0010\u0015J$\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00102\u0006\u0010'\u001a\u00020&H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010)J$\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b+\u0010\u0015J$\u0010,\u001a\b\u0012\u0004\u0012\u00020&0\u00102\u0006\u0010'\u001a\u00020&H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u0010)R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006."}, d2 = {"Lcom/mlk/taskmanager/service/CalendarSyncService;", "", "context", "Landroid/content/Context;", "routineRepository", "Lcom/mlk/taskmanager/data/repository/RoutineRepository;", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "(Landroid/content/Context;Lcom/mlk/taskmanager/data/repository/RoutineRepository;Lcom/mlk/taskmanager/data/repository/TaskRepository;)V", "calendarService", "Lcom/google/api/services/calendar/Calendar;", "currentAccount", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "tag", "", "deleteCalendarEvent", "Lkotlin/Result;", "", "routine", "Lcom/mlk/taskmanager/data/model/Routine;", "deleteCalendarEvent-gIAlu-s", "(Lcom/mlk/taskmanager/data/model/Routine;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGoogleSignInOptions", "Lcom/google/android/gms/auth/api/signin/GoogleSignInOptions;", "initializeCalendarService", "", "signInAccount", "(Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSignedIn", "syncAllRoutines", "", "syncAllRoutines-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncAllTasks", "syncAllTasks-IoAF18A", "syncRoutineWithCalendar", "syncRoutineWithCalendar-gIAlu-s", "syncTaskWithCalendar", "Lcom/mlk/taskmanager/data/model/Task;", "task", "syncTaskWithCalendar-gIAlu-s", "(Lcom/mlk/taskmanager/data/model/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCalendarEvent", "updateCalendarEvent-gIAlu-s", "updateCalendarEventForTask", "updateCalendarEventForTask-gIAlu-s", "app_debug"})
public final class CalendarSyncService {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.RoutineRepository routineRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.TaskRepository taskRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tag = "CalendarSyncService";
    @org.jetbrains.annotations.Nullable()
    private com.google.api.services.calendar.Calendar calendarService;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.auth.api.signin.GoogleSignInAccount currentAccount;
    
    @javax.inject.Inject()
    public CalendarSyncService(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.RoutineRepository routineRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.TaskRepository taskRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initializeCalendarService(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.auth.api.signin.GoogleSignInAccount signInAccount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final boolean isSignedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.auth.api.signin.GoogleSignInOptions getGoogleSignInOptions() {
        return null;
    }
}