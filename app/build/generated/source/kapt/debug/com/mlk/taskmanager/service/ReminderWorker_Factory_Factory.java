package com.mlk.taskmanager.service;

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
public final class ReminderWorker_Factory_Factory implements Factory<ReminderWorker.Factory> {
  private final Provider<TimeReminderService> timeReminderServiceProvider;

  public ReminderWorker_Factory_Factory(Provider<TimeReminderService> timeReminderServiceProvider) {
    this.timeReminderServiceProvider = timeReminderServiceProvider;
  }

  @Override
  public ReminderWorker.Factory get() {
    return newInstance(timeReminderServiceProvider.get());
  }

  public static ReminderWorker_Factory_Factory create(
      Provider<TimeReminderService> timeReminderServiceProvider) {
    return new ReminderWorker_Factory_Factory(timeReminderServiceProvider);
  }

  public static ReminderWorker.Factory newInstance(TimeReminderService timeReminderService) {
    return new ReminderWorker.Factory(timeReminderService);
  }
}
