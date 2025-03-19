package com.mlk.taskmanager.ui.home;

import android.content.Context;
import com.mlk.taskmanager.data.repository.ProjectRepository;
import com.mlk.taskmanager.data.repository.RoutineRepository;
import com.mlk.taskmanager.data.repository.TaskRepository;
import com.mlk.taskmanager.data.repository.WeatherRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<ProjectRepository> projectRepositoryProvider;

  private final Provider<WeatherRepository> weatherRepositoryProvider;

  private final Provider<Context> contextProvider;

  public HomeViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<ProjectRepository> projectRepositoryProvider,
      Provider<WeatherRepository> weatherRepositoryProvider, Provider<Context> contextProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.projectRepositoryProvider = projectRepositoryProvider;
    this.weatherRepositoryProvider = weatherRepositoryProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(taskRepositoryProvider.get(), routineRepositoryProvider.get(), projectRepositoryProvider.get(), weatherRepositoryProvider.get(), contextProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<ProjectRepository> projectRepositoryProvider,
      Provider<WeatherRepository> weatherRepositoryProvider, Provider<Context> contextProvider) {
    return new HomeViewModel_Factory(taskRepositoryProvider, routineRepositoryProvider, projectRepositoryProvider, weatherRepositoryProvider, contextProvider);
  }

  public static HomeViewModel newInstance(TaskRepository taskRepository,
      RoutineRepository routineRepository, ProjectRepository projectRepository,
      WeatherRepository weatherRepository, Context context) {
    return new HomeViewModel(taskRepository, routineRepository, projectRepository, weatherRepository, context);
  }
}
