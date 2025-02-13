package com.mlk.taskmanager.service;

import android.app.NotificationManager;
import android.content.Context;
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
public final class LocationReminderService_Factory implements Factory<LocationReminderService> {
  private final Provider<Context> contextProvider;

  private final Provider<NotificationManager> notificationManagerProvider;

  public LocationReminderService_Factory(Provider<Context> contextProvider,
      Provider<NotificationManager> notificationManagerProvider) {
    this.contextProvider = contextProvider;
    this.notificationManagerProvider = notificationManagerProvider;
  }

  @Override
  public LocationReminderService get() {
    return newInstance(contextProvider.get(), notificationManagerProvider.get());
  }

  public static LocationReminderService_Factory create(Provider<Context> contextProvider,
      Provider<NotificationManager> notificationManagerProvider) {
    return new LocationReminderService_Factory(contextProvider, notificationManagerProvider);
  }

  public static LocationReminderService newInstance(Context context,
      NotificationManager notificationManager) {
    return new LocationReminderService(context, notificationManager);
  }
}
