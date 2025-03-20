package com.mlk.taskmanager.ui.routines;

import com.mlk.taskmanager.data.repository.RoutineRepository;
import com.mlk.taskmanager.service.CalendarSyncService;
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
public final class RoutinesViewModel_Factory implements Factory<RoutinesViewModel> {
  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<CalendarSyncService> calendarSyncServiceProvider;

  public RoutinesViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.calendarSyncServiceProvider = calendarSyncServiceProvider;
  }

  @Override
  public RoutinesViewModel get() {
    return newInstance(routineRepositoryProvider.get(), calendarSyncServiceProvider.get());
  }

  public static RoutinesViewModel_Factory create(
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider) {
    return new RoutinesViewModel_Factory(routineRepositoryProvider, calendarSyncServiceProvider);
  }

  public static RoutinesViewModel newInstance(RoutineRepository routineRepository,
      CalendarSyncService calendarSyncService) {
    return new RoutinesViewModel(routineRepository, calendarSyncService);
  }
}
