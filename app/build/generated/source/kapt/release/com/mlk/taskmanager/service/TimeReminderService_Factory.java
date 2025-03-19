package com.mlk.taskmanager.service;

import android.app.NotificationManager;
import android.content.Context;
import androidx.work.WorkManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class TimeReminderService_Factory implements Factory<TimeReminderService> {
  private final Provider<Context> contextProvider;

  private final Provider<WorkManager> workManagerProvider;

  private final Provider<NotificationManager> notificationManagerProvider;

  public TimeReminderService_Factory(Provider<Context> contextProvider,
      Provider<WorkManager> workManagerProvider,
      Provider<NotificationManager> notificationManagerProvider) {
    this.contextProvider = contextProvider;
    this.workManagerProvider = workManagerProvider;
    this.notificationManagerProvider = notificationManagerProvider;
  }

  @Override
  public TimeReminderService get() {
    return newInstance(contextProvider.get(), workManagerProvider.get(), notificationManagerProvider.get());
  }

  public static TimeReminderService_Factory create(Provider<Context> contextProvider,
      Provider<WorkManager> workManagerProvider,
      Provider<NotificationManager> notificationManagerProvider) {
    return new TimeReminderService_Factory(contextProvider, workManagerProvider, notificationManagerProvider);
  }

  public static TimeReminderService newInstance(Context context, WorkManager workManager,
      NotificationManager notificationManager) {
    return new TimeReminderService(context, workManager, notificationManager);
  }
}
