package com.mlk.taskmanager.ui.pomodoro;

import android.content.Context;
import com.mlk.taskmanager.service.NotificationManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class PomodoroViewModel_Factory implements Factory<PomodoroViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<NotificationManager> notificationManagerProvider;

  private final Provider<android.app.NotificationManager> androidNotificationManagerProvider;

  public PomodoroViewModel_Factory(Provider<Context> contextProvider,
      Provider<NotificationManager> notificationManagerProvider,
      Provider<android.app.NotificationManager> androidNotificationManagerProvider) {
    this.contextProvider = contextProvider;
    this.notificationManagerProvider = notificationManagerProvider;
    this.androidNotificationManagerProvider = androidNotificationManagerProvider;
  }

  @Override
  public PomodoroViewModel get() {
    return newInstance(contextProvider.get(), notificationManagerProvider.get(), androidNotificationManagerProvider.get());
  }

  public static PomodoroViewModel_Factory create(Provider<Context> contextProvider,
      Provider<NotificationManager> notificationManagerProvider,
      Provider<android.app.NotificationManager> androidNotificationManagerProvider) {
    return new PomodoroViewModel_Factory(contextProvider, notificationManagerProvider, androidNotificationManagerProvider);
  }

  public static PomodoroViewModel newInstance(Context context,
      NotificationManager notificationManager,
      android.app.NotificationManager androidNotificationManager) {
    return new PomodoroViewModel(context, notificationManager, androidNotificationManager);
  }
}
