package com.mlk.taskmanager.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/mlk/taskmanager/di/AppModule;", "", "()V", "provideNotificationManager", "Lcom/mlk/taskmanager/service/NotificationManager;", "context", "Landroid/content/Context;", "locationReminderService", "Lcom/mlk/taskmanager/service/LocationReminderService;", "provideSettingsRepository", "Lcom/mlk/taskmanager/data/repository/SettingsRepository;", "provideTaskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "database", "Lcom/mlk/taskmanager/data/local/TaskDatabase;", "projectRepository", "Lcom/mlk/taskmanager/data/repository/ProjectRepository;", "provideWeatherRepository", "Lcom/mlk/taskmanager/data/repository/WeatherRepository;", "weatherApiService", "Lcom/mlk/taskmanager/data/api/WeatherApiService;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.repository.TaskRepository provideTaskRepository(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.local.TaskDatabase database, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.ProjectRepository projectRepository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.repository.SettingsRepository provideSettingsRepository(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.service.NotificationManager provideNotificationManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.LocationReminderService locationReminderService) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.repository.WeatherRepository provideWeatherRepository(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.api.WeatherApiService weatherApiService, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}