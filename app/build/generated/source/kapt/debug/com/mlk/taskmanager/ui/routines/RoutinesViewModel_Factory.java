package com.mlk.taskmanager.ui.routines;

import com.mlk.taskmanager.data.repository.RoutineRepository;
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

  public RoutinesViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
  }

  @Override
  public RoutinesViewModel get() {
    return newInstance(routineRepositoryProvider.get());
  }

  public static RoutinesViewModel_Factory create(
      Provider<RoutineRepository> routineRepositoryProvider) {
    return new RoutinesViewModel_Factory(routineRepositoryProvider);
  }

  public static RoutinesViewModel newInstance(RoutineRepository routineRepository) {
    return new RoutinesViewModel(routineRepository);
  }
}
