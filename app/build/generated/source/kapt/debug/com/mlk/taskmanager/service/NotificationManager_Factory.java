package com.mlk.taskmanager.service;

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
public final class NotificationManager_Factory implements Factory<NotificationManager> {
  private final Provider<Context> contextProvider;

  private final Provider<LocationReminderService> locationReminderServiceProvider;

  public NotificationManager_Factory(Provider<Context> contextProvider,
      Provider<LocationReminderService> locationReminderServiceProvider) {
    this.contextProvider = contextProvider;
    this.locationReminderServiceProvider = locationReminderServiceProvider;
  }

  @Override
  public NotificationManager get() {
    return newInstance(contextProvider.get(), locationReminderServiceProvider.get());
  }

  public static NotificationManager_Factory create(Provider<Context> contextProvider,
      Provider<LocationReminderService> locationReminderServiceProvider) {
    return new NotificationManager_Factory(contextProvider, locationReminderServiceProvider);
  }

  public static NotificationManager newInstance(Context context,
      LocationReminderService locationReminderService) {
    return new NotificationManager(context, locationReminderService);
  }
}
