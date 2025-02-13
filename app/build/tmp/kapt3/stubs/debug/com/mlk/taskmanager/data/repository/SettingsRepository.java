package com.mlk.taskmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0012\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u001c\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010 \u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010!\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\"\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0017J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006$"}, d2 = {"Lcom/mlk/taskmanager/data/repository/SettingsRepository;", "", "areNotificationsEnabled", "Lkotlinx/coroutines/flow/Flow;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategories", "", "", "getDefaultLocationRadius", "", "getDefaultReminderTime", "Ljava/time/LocalTime;", "isDarkMode", "isLocationEnabled", "isSoundEnabled", "isVibrationEnabled", "setCategories", "", "categories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDarkMode", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDefaultLocationRadius", "radius", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDefaultReminderTime", "time", "(Ljava/time/LocalTime;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDynamicColors", "setLocationEnabled", "setNotificationsEnabled", "setSoundEnabled", "setVibrationEnabled", "useDynamicColors", "app_debug"})
public abstract interface SettingsRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isDarkMode(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Boolean>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setDarkMode(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object useDynamicColors(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Boolean>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setDynamicColors(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object areNotificationsEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Boolean>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setNotificationsEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isSoundEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Boolean>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setSoundEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isVibrationEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Boolean>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setVibrationEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isLocationEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Boolean>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setLocationEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDefaultLocationRadius(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Float>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setDefaultLocationRadius(float radius, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends java.util.List<java.lang.String>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setCategories(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> categories, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDefaultReminderTime(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.time.LocalTime>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setDefaultReminderTime(@org.jetbrains.annotations.NotNull()
    java.time.LocalTime time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}