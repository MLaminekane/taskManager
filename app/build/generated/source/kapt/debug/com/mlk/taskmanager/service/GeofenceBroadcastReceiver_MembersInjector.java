package com.mlk.taskmanager.service;

import com.mlk.taskmanager.data.repository.TaskRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class GeofenceBroadcastReceiver_MembersInjector implements MembersInjector<GeofenceBroadcastReceiver> {
  private final Provider<LocationReminderService> locationReminderServiceProvider;

  private final Provider<TaskRepository> taskRepositoryProvider;

  public GeofenceBroadcastReceiver_MembersInjector(
      Provider<LocationReminderService> locationReminderServiceProvider,
      Provider<TaskRepository> taskRepositoryProvider) {
    this.locationReminderServiceProvider = locationReminderServiceProvider;
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  public static MembersInjector<GeofenceBroadcastReceiver> create(
      Provider<LocationReminderService> locationReminderServiceProvider,
      Provider<TaskRepository> taskRepositoryProvider) {
    return new GeofenceBroadcastReceiver_MembersInjector(locationReminderServiceProvider, taskRepositoryProvider);
  }

  @Override
  public void injectMembers(GeofenceBroadcastReceiver instance) {
    injectLocationReminderService(instance, locationReminderServiceProvider.get());
    injectTaskRepository(instance, taskRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.mlk.taskmanager.service.GeofenceBroadcastReceiver.locationReminderService")
  public static void injectLocationReminderService(GeofenceBroadcastReceiver instance,
      LocationReminderService locationReminderService) {
    instance.locationReminderService = locationReminderService;
  }

  @InjectedFieldSignature("com.mlk.taskmanager.service.GeofenceBroadcastReceiver.taskRepository")
  public static void injectTaskRepository(GeofenceBroadcastReceiver instance,
      TaskRepository taskRepository) {
    instance.taskRepository = taskRepository;
  }
}
