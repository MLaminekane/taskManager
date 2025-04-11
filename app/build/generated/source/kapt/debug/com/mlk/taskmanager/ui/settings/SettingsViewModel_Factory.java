package com.mlk.taskmanager.ui.settings;

import android.content.Context;
import com.mlk.taskmanager.data.repository.SettingsRepository;
import com.mlk.taskmanager.data.repository.UserRepository;
import com.mlk.taskmanager.service.CalendarSyncService;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<CalendarSyncService> calendarSyncServiceProvider;

  private final Provider<Context> contextProvider;

  public SettingsViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider,
      Provider<Context> contextProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.calendarSyncServiceProvider = calendarSyncServiceProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), userRepositoryProvider.get(), calendarSyncServiceProvider.get(), contextProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<CalendarSyncService> calendarSyncServiceProvider,
      Provider<Context> contextProvider) {
    return new SettingsViewModel_Factory(settingsRepositoryProvider, userRepositoryProvider, calendarSyncServiceProvider, contextProvider);
  }

  public static SettingsViewModel newInstance(SettingsRepository settingsRepository,
      UserRepository userRepository, CalendarSyncService calendarSyncService, Context context) {
    return new SettingsViewModel(settingsRepository, userRepository, calendarSyncService, context);
  }
}
