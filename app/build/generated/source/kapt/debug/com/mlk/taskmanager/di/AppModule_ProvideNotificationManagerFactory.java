package com.mlk.taskmanager.di;

import android.content.Context;
import com.mlk.taskmanager.service.LocationReminderService;
import com.mlk.taskmanager.service.NotificationManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideNotificationManagerFactory implements Factory<NotificationManager> {
  private final Provider<Context> contextProvider;

  private final Provider<LocationReminderService> locationReminderServiceProvider;

  public AppModule_ProvideNotificationManagerFactory(Provider<Context> contextProvider,
      Provider<LocationReminderService> locationReminderServiceProvider) {
    this.contextProvider = contextProvider;
    this.locationReminderServiceProvider = locationReminderServiceProvider;
  }

  @Override
  public NotificationManager get() {
    return provideNotificationManager(contextProvider.get(), locationReminderServiceProvider.get());
  }

  public static AppModule_ProvideNotificationManagerFactory create(
      Provider<Context> contextProvider,
      Provider<LocationReminderService> locationReminderServiceProvider) {
    return new AppModule_ProvideNotificationManagerFactory(contextProvider, locationReminderServiceProvider);
  }

  public static NotificationManager provideNotificationManager(Context context,
      LocationReminderService locationReminderService) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideNotificationManager(context, locationReminderService));
  }
}
