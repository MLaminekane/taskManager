package com.mlk.taskmanager.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00b1\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\u0002\u0010\u0018J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0017H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\nH\u00c6\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\t\u00106\u001a\u00020\u000fH\u00c6\u0003J\u00b5\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00c6\u0001J\u0013\u00108\u001a\u00020\u00032\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020;H\u00d6\u0001J\t\u0010<\u001a\u00020\rH\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u001aR\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001aR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001a\u00a8\u0006="}, d2 = {"Lcom/mlk/taskmanager/ui/settings/SettingsUiState;", "", "isDarkMode", "", "useDynamicColors", "areNotificationsEnabled", "isSoundEnabled", "isVibrationEnabled", "isLocationEnabled", "defaultLocationRadius", "", "categories", "", "", "defaultReminderTime", "Ljava/time/LocalTime;", "isCalendarSyncEnabled", "isGoogleSignedIn", "googleAccountEmail", "isSyncing", "syncError", "isUserLoggedIn", "currentUser", "Lcom/mlk/taskmanager/data/model/User;", "(ZZZZZZFLjava/util/List;Ljava/time/LocalTime;ZZLjava/lang/String;ZLjava/lang/String;ZLcom/mlk/taskmanager/data/model/User;)V", "getAreNotificationsEnabled", "()Z", "getCategories", "()Ljava/util/List;", "getCurrentUser", "()Lcom/mlk/taskmanager/data/model/User;", "getDefaultLocationRadius", "()F", "getDefaultReminderTime", "()Ljava/time/LocalTime;", "getGoogleAccountEmail", "()Ljava/lang/String;", "getSyncError", "getUseDynamicColors", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class SettingsUiState {
    private final boolean isDarkMode = false;
    private final boolean useDynamicColors = false;
    private final boolean areNotificationsEnabled = false;
    private final boolean isSoundEnabled = false;
    private final boolean isVibrationEnabled = false;
    private final boolean isLocationEnabled = false;
    private final float defaultLocationRadius = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> categories = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalTime defaultReminderTime = null;
    private final boolean isCalendarSyncEnabled = false;
    private final boolean isGoogleSignedIn = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String googleAccountEmail = null;
    private final boolean isSyncing = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String syncError = null;
    private final boolean isUserLoggedIn = false;
    @org.jetbrains.annotations.Nullable()
    private final com.mlk.taskmanager.data.model.User currentUser = null;
    
    public SettingsUiState(boolean isDarkMode, boolean useDynamicColors, boolean areNotificationsEnabled, boolean isSoundEnabled, boolean isVibrationEnabled, boolean isLocationEnabled, float defaultLocationRadius, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> categories, @org.jetbrains.annotations.NotNull()
    java.time.LocalTime defaultReminderTime, boolean isCalendarSyncEnabled, boolean isGoogleSignedIn, @org.jetbrains.annotations.Nullable()
    java.lang.String googleAccountEmail, boolean isSyncing, @org.jetbrains.annotations.Nullable()
    java.lang.String syncError, boolean isUserLoggedIn, @org.jetbrains.annotations.Nullable()
    com.mlk.taskmanager.data.model.User currentUser) {
        super();
    }
    
    public final boolean isDarkMode() {
        return false;
    }
    
    public final boolean getUseDynamicColors() {
        return false;
    }
    
    public final boolean getAreNotificationsEnabled() {
        return false;
    }
    
    public final boolean isSoundEnabled() {
        return false;
    }
    
    public final boolean isVibrationEnabled() {
        return false;
    }
    
    public final boolean isLocationEnabled() {
        return false;
    }
    
    public final float getDefaultLocationRadius() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalTime getDefaultReminderTime() {
        return null;
    }
    
    public final boolean isCalendarSyncEnabled() {
        return false;
    }
    
    public final boolean isGoogleSignedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getGoogleAccountEmail() {
        return null;
    }
    
    public final boolean isSyncing() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSyncError() {
        return null;
    }
    
    public final boolean isUserLoggedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.mlk.taskmanager.data.model.User getCurrentUser() {
        return null;
    }
    
    public SettingsUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    public final boolean component13() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    public final boolean component15() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.mlk.taskmanager.data.model.User component16() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final float component7() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalTime component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.settings.SettingsUiState copy(boolean isDarkMode, boolean useDynamicColors, boolean areNotificationsEnabled, boolean isSoundEnabled, boolean isVibrationEnabled, boolean isLocationEnabled, float defaultLocationRadius, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> categories, @org.jetbrains.annotations.NotNull()
    java.time.LocalTime defaultReminderTime, boolean isCalendarSyncEnabled, boolean isGoogleSignedIn, @org.jetbrains.annotations.Nullable()
    java.lang.String googleAccountEmail, boolean isSyncing, @org.jetbrains.annotations.Nullable()
    java.lang.String syncError, boolean isUserLoggedIn, @org.jetbrains.annotations.Nullable()
    com.mlk.taskmanager.data.model.User currentUser) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}