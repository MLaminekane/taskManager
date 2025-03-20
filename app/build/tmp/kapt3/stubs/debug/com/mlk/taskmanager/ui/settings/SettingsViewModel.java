package com.mlk.taskmanager.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0015H\u0002J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010\'\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010(\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010)\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010*\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017J\u0006\u0010+\u001a\u00020\u0013J\u0006\u0010,\u001a\u00020\u0013J\u0006\u0010-\u001a\u00020\u0013J\u0006\u0010.\u001a\u00020\u0013J\u0006\u0010/\u001a\u00020\u0013R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u00060"}, d2 = {"Lcom/mlk/taskmanager/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/mlk/taskmanager/data/repository/SettingsRepository;", "calendarSyncService", "Lcom/mlk/taskmanager/service/CalendarSyncService;", "context", "Landroid/content/Context;", "(Lcom/mlk/taskmanager/data/repository/SettingsRepository;Lcom/mlk/taskmanager/service/CalendarSyncService;Landroid/content/Context;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/settings/SettingsUiState;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addCategory", "", "category", "", "checkGoogleSignIn", "", "getGoogleAccountEmail", "handleSignInResult", "result", "Landroidx/activity/result/ActivityResult;", "removeCategory", "setCalendarSyncEnabled", "enabled", "setDarkMode", "setDefaultLocationRadius", "radius", "", "setDefaultReminderTime", "time", "Ljava/time/LocalTime;", "setDynamicColors", "setLocationEnabled", "setNotificationsEnabled", "setSoundEnabled", "setVibrationEnabled", "signInToGoogle", "signOutFromGoogle", "syncAllRoutines", "toggleDarkMode", "toggleNotifications", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.CalendarSyncService calendarSyncService = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.settings.SettingsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.settings.SettingsUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.CalendarSyncService calendarSyncService, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.settings.SettingsUiState> getUiState() {
        return null;
    }
    
    private final java.lang.String getGoogleAccountEmail() {
        return null;
    }
    
    private final boolean checkGoogleSignIn() {
        return false;
    }
    
    public final void setDarkMode(boolean enabled) {
    }
    
    public final void setDynamicColors(boolean enabled) {
    }
    
    public final void setNotificationsEnabled(boolean enabled) {
    }
    
    public final void setSoundEnabled(boolean enabled) {
    }
    
    public final void setVibrationEnabled(boolean enabled) {
    }
    
    public final void setLocationEnabled(boolean enabled) {
    }
    
    public final void setDefaultLocationRadius(float radius) {
    }
    
    public final void setCalendarSyncEnabled(boolean enabled) {
    }
    
    public final void addCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category) {
    }
    
    public final void removeCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category) {
    }
    
    public final void setDefaultReminderTime(@org.jetbrains.annotations.NotNull()
    java.time.LocalTime time) {
    }
    
    public final void toggleDarkMode() {
    }
    
    public final void toggleNotifications() {
    }
    
    public final void signInToGoogle() {
    }
    
    public final void handleSignInResult(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResult result) {
    }
    
    public final void signOutFromGoogle() {
    }
    
    public final void syncAllRoutines() {
    }
}