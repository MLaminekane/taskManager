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
public final class BootCompletedReceiver_MembersInjector implements MembersInjector<BootCompletedReceiver> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<NotificationManager> notificationManagerProvider;

  public BootCompletedReceiver_MembersInjector(Provider<TaskRepository> taskRepositoryProvider,
      Provider<NotificationManager> notificationManagerProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.notificationManagerProvider = notificationManagerProvider;
  }

  public static MembersInjector<BootCompletedReceiver> create(
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<NotificationManager> notificationManagerProvider) {
    return new BootCompletedReceiver_MembersInjector(taskRepositoryProvider, notificationManagerProvider);
  }

  @Override
  public void injectMembers(BootCompletedReceiver instance) {
    injectTaskRepository(instance, taskRepositoryProvider.get());
    injectNotificationManager(instance, notificationManagerProvider.get());
  }

  @InjectedFieldSignature("com.mlk.taskmanager.service.BootCompletedReceiver.taskRepository")
  public static void injectTaskRepository(BootCompletedReceiver instance,
      TaskRepository taskRepository) {
    instance.taskRepository = taskRepository;
  }

  @InjectedFieldSignature("com.mlk.taskmanager.service.BootCompletedReceiver.notificationManager")
  public static void injectNotificationManager(BootCompletedReceiver instance,
      NotificationManager notificationManager) {
    instance.notificationManager = notificationManager;
  }
}
