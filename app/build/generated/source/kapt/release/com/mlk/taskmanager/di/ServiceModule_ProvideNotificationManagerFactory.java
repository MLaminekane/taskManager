package com.mlk.taskmanager.di;

import android.app.NotificationManager;
import android.content.Context;
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
public final class ServiceModule_ProvideNotificationManagerFactory implements Factory<NotificationManager> {
  private final Provider<Context> contextProvider;

  public ServiceModule_ProvideNotificationManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NotificationManager get() {
    return provideNotificationManager(contextProvider.get());
  }

  public static ServiceModule_ProvideNotificationManagerFactory create(
      Provider<Context> contextProvider) {
    return new ServiceModule_ProvideNotificationManagerFactory(contextProvider);
  }

  public static NotificationManager provideNotificationManager(Context context) {
    return Preconditions.checkNotNullFromProvides(ServiceModule.INSTANCE.provideNotificationManager(context));
  }
}
