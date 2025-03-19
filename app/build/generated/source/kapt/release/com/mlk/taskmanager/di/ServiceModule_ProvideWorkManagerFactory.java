package com.mlk.taskmanager.di;

import android.content.Context;
import androidx.work.WorkManager;
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
public final class ServiceModule_ProvideWorkManagerFactory implements Factory<WorkManager> {
  private final Provider<Context> contextProvider;

  public ServiceModule_ProvideWorkManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public WorkManager get() {
    return provideWorkManager(contextProvider.get());
  }

  public static ServiceModule_ProvideWorkManagerFactory create(Provider<Context> contextProvider) {
    return new ServiceModule_ProvideWorkManagerFactory(contextProvider);
  }

  public static WorkManager provideWorkManager(Context context) {
    return Preconditions.checkNotNullFromProvides(ServiceModule.INSTANCE.provideWorkManager(context));
  }
}
