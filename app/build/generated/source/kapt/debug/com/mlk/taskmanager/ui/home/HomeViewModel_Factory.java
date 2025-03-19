package com.mlk.taskmanager.ui.home;

import com.mlk.taskmanager.data.repository.ProjectRepository;
import com.mlk.taskmanager.data.repository.RoutineRepository;
import com.mlk.taskmanager.data.repository.TaskRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<ProjectRepository> projectRepositoryProvider;

  public HomeViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<ProjectRepository> projectRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.projectRepositoryProvider = projectRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(taskRepositoryProvider.get(), routineRepositoryProvider.get(), projectRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<ProjectRepository> projectRepositoryProvider) {
    return new HomeViewModel_Factory(taskRepositoryProvider, routineRepositoryProvider, projectRepositoryProvider);
  }

  public static HomeViewModel newInstance(TaskRepository taskRepository,
      RoutineRepository routineRepository, ProjectRepository projectRepository) {
    return new HomeViewModel(taskRepository, routineRepository, projectRepository);
  }
}
