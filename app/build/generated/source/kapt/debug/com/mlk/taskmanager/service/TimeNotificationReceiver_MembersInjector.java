package com.mlk.taskmanager.service;

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
public final class TimeNotificationReceiver_MembersInjector implements MembersInjector<TimeNotificationReceiver> {
  private final Provider<NotificationManager> notificationManagerProvider;

  public TimeNotificationReceiver_MembersInjector(
      Provider<NotificationManager> notificationManagerProvider) {
    this.notificationManagerProvider = notificationManagerProvider;
  }

  public static MembersInjector<TimeNotificationReceiver> create(
      Provider<NotificationManager> notificationManagerProvider) {
    return new TimeNotificationReceiver_MembersInjector(notificationManagerProvider);
  }

  @Override
  public void injectMembers(TimeNotificationReceiver instance) {
    injectNotificationManager(instance, notificationManagerProvider.get());
  }

  @InjectedFieldSignature("com.mlk.taskmanager.service.TimeNotificationReceiver.notificationManager")
  public static void injectNotificationManager(TimeNotificationReceiver instance,
      NotificationManager notificationManager) {
    instance.notificationManager = notificationManager;
  }
}
