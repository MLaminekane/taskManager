package com.mlk.taskmanager.service;

import android.content.Context;
import com.mlk.taskmanager.data.repository.RoutineRepository;
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
public final class CalendarSyncService_Factory implements Factory<CalendarSyncService> {
  private final Provider<Context> contextProvider;

  private final Provider<RoutineRepository> routineRepositoryProvider;

  public CalendarSyncService_Factory(Provider<Context> contextProvider,
      Provider<RoutineRepository> routineRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.routineRepositoryProvider = routineRepositoryProvider;
  }

  @Override
  public CalendarSyncService get() {
    return newInstance(contextProvider.get(), routineRepositoryProvider.get());
  }

  public static CalendarSyncService_Factory create(Provider<Context> contextProvider,
      Provider<RoutineRepository> routineRepositoryProvider) {
    return new CalendarSyncService_Factory(contextProvider, routineRepositoryProvider);
  }

  public static CalendarSyncService newInstance(Context context,
      RoutineRepository routineRepository) {
    return new CalendarSyncService(context, routineRepository);
  }
}
