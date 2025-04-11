package com.mlk.taskmanager.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0017H\u0002J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u0015H\u0002J\u0006\u0010\u001f\u001a\u00020\u0015J\u000e\u0010 \u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010#\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010+\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010,\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010-\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010.\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010/\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\tJ\u000e\u00100\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\tJ\u0006\u00101\u001a\u00020\u000fJ\u0006\u00102\u001a\u00020\u0015J\u0006\u00103\u001a\u00020\u0015J\u0006\u00104\u001a\u00020\u0015J\u0006\u00105\u001a\u00020\u0015J\u0006\u00106\u001a\u00020\u0015J\u0006\u00107\u001a\u00020\u0015R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/mlk/taskmanager/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/mlk/taskmanager/data/repository/SettingsRepository;", "userRepository", "Lcom/mlk/taskmanager/data/repository/UserRepository;", "calendarSyncService", "Lcom/mlk/taskmanager/service/CalendarSyncService;", "context", "Landroid/content/Context;", "(Lcom/mlk/taskmanager/data/repository/SettingsRepository;Lcom/mlk/taskmanager/data/repository/UserRepository;Lcom/mlk/taskmanager/service/CalendarSyncService;Landroid/content/Context;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/settings/SettingsUiState;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addCategory", "", "category", "", "checkGoogleSignIn", "", "getGoogleAccountEmail", "handleSignInResult", "result", "Landroidx/activity/result/ActivityResult;", "loadSettings", "logout", "removeCategory", "setCalendarSyncEnabled", "enabled", "setDarkMode", "setDefaultLocationRadius", "radius", "", "setDefaultReminderTime", "time", "Ljava/time/LocalTime;", "setDynamicColors", "setLocationEnabled", "setNotificationsEnabled", "setSoundEnabled", "setVibrationEnabled", "showLicenses", "showPrivacyPolicy", "signInToGoogle", "signOutFromGoogle", "syncAll", "syncAllRoutines", "syncAllTasks", "toggleDarkMode", "toggleNotifications", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.UserRepository userRepository = null;
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
    com.mlk.taskmanager.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.CalendarSyncService calendarSyncService, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.settings.SettingsUiState> getUiState() {
        return null;
    }
    
    private final void loadSettings() {
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
    
    public final void logout() {
    }
    
    public final void handleSignInResult(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResult result) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.auth.api.signin.GoogleSignInClient signInToGoogle() {
        return null;
    }
    
    public final void signOutFromGoogle() {
    }
    
    public final void syncAllRoutines() {
    }
    
    public final void syncAllTasks() {
    }
    
    public final void syncAll() {
    }
    
    public final void showLicenses(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void showPrivacyPolicy(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}