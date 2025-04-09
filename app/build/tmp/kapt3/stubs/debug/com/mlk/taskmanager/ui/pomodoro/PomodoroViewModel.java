package com.mlk.taskmanager.ui.pomodoro;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010\u0019\u001a\u00020\u0017J\u0006\u0010\u001a\u001a\u00020\u0017J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0013H\u0002J\u0006\u0010\u001d\u001a\u00020\u0017J\u0006\u0010\u001e\u001a\u00020\u0017J\u0006\u0010\u001f\u001a\u00020\u0017J\b\u0010 \u001a\u00020\u0017H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006!"}, d2 = {"Lcom/mlk/taskmanager/ui/pomodoro/PomodoroViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "notificationManager", "Lcom/mlk/taskmanager/service/NotificationManager;", "androidNotificationManager", "Landroid/app/NotificationManager;", "(Landroid/content/Context;Lcom/mlk/taskmanager/service/NotificationManager;Landroid/app/NotificationManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/pomodoro/PomodoroUiState;", "timer", "Landroid/os/CountDownTimer;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "calculateFocusRate", "", "sessions", "totalMinutes", "onCleared", "", "pauseTimer", "resetTimer", "skipSession", "startCountdown", "minutes", "startTimer", "toggleDnd", "toggleNotificationBlocking", "updateNotificationSettings", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PomodoroViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.NotificationManager notificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private final android.app.NotificationManager androidNotificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.pomodoro.PomodoroUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.pomodoro.PomodoroUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer timer;
    
    @javax.inject.Inject()
    public PomodoroViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.NotificationManager notificationManager, @org.jetbrains.annotations.NotNull()
    android.app.NotificationManager androidNotificationManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.pomodoro.PomodoroUiState> getUiState() {
        return null;
    }
    
    private final void startCountdown(int minutes) {
    }
    
    private final int calculateFocusRate(int sessions, int totalMinutes) {
        return 0;
    }
    
    public final void startTimer() {
    }
    
    public final void pauseTimer() {
    }
    
    public final void resetTimer() {
    }
    
    public final void skipSession() {
    }
    
    public final void toggleDnd() {
    }
    
    public final void toggleNotificationBlocking() {
    }
    
    private final void updateNotificationSettings() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}