package com.mlk.taskmanager.ui.tasks;

import com.mlk.taskmanager.data.repository.ProjectRepository;
import com.mlk.taskmanager.data.repository.SettingsRepository;
import com.mlk.taskmanager.data.repository.TaskRepository;
import com.mlk.taskmanager.service.CalendarSyncService;
import com.mlk.taskmanager.service.LocationReminderService;
import com.mlk.taskmanager.service.NotificationManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class TasksViewModel_Factory implements Factory<TasksViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<ProjectRepository> projectRepositoryProvider;

  private final Provider<LocationReminderService> locationReminderServiceProvider;

  private final Provider<NotificationManager> notificationManagerProvider;

  private final Provider<CalendarSyncService> calendarSyncServiceProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public TasksViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<ProjectRepository> projectRepositoryProvider,
      Provider<LocationReminderService> locationReminderServiceProvider,
      Provider<NotificationManager> notificationManagerProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.projectRepositoryProvider = projectRepositoryProvider;
    this.locationReminderServiceProvider = locationReminderServiceProvider;
    this.notificationManagerProvider = notificationManagerProvider;
    this.calendarSyncServiceProvider = calendarSyncServiceProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public TasksViewModel get() {
    return newInstance(taskRepositoryProvider.get(), projectRepositoryProvider.get(), locationReminderServiceProvider.get(), notificationManagerProvider.get(), calendarSyncServiceProvider.get(), settingsRepositoryProvider.get());
  }

  public static TasksViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<ProjectRepository> projectRepositoryProvider,
      Provider<LocationReminderService> locationReminderServiceProvider,
      Provider<NotificationManager> notificationManagerProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new TasksViewModel_Factory(taskRepositoryProvider, projectRepositoryProvider, locationReminderServiceProvider, notificationManagerProvider, calendarSyncServiceProvider, settingsRepositoryProvider);
  }

  public static TasksViewModel newInstance(TaskRepository taskRepository,
      ProjectRepository projectRepository, LocationReminderService locationReminderService,
      NotificationManager notificationManager, CalendarSyncService calendarSyncService,
      SettingsRepository settingsRepository) {
    return new TasksViewModel(taskRepository, projectRepository, locationReminderService, notificationManager, calendarSyncService, settingsRepository);
  }
}
