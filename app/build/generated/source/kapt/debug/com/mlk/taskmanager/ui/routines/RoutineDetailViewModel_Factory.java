package com.mlk.taskmanager.ui.routines;

import androidx.lifecycle.SavedStateHandle;
import com.mlk.taskmanager.data.repository.RoutineRepository;
import com.mlk.taskmanager.data.repository.SettingsRepository;
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
public final class RoutineDetailViewModel_Factory implements Factory<RoutineDetailViewModel> {
  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<CalendarSyncService> calendarSyncServiceProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public RoutineDetailViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.calendarSyncServiceProvider = calendarSyncServiceProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public RoutineDetailViewModel get() {
    return newInstance(routineRepositoryProvider.get(), calendarSyncServiceProvider.get(), settingsRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static RoutineDetailViewModel_Factory create(
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new RoutineDetailViewModel_Factory(routineRepositoryProvider, calendarSyncServiceProvider, settingsRepositoryProvider, savedStateHandleProvider);
  }

  public static RoutineDetailViewModel newInstance(RoutineRepository routineRepository,
      CalendarSyncService calendarSyncService, SettingsRepository settingsRepository,
      SavedStateHandle savedStateHandle) {
    return new RoutineDetailViewModel(routineRepository, calendarSyncService, settingsRepository, savedStateHandle);
  }
}
