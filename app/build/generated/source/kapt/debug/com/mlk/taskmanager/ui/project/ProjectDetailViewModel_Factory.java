package com.mlk.taskmanager.ui.project;

import com.mlk.taskmanager.data.repository.ProjectRepository;
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
public final class ProjectDetailViewModel_Factory implements Factory<ProjectDetailViewModel> {
  private final Provider<ProjectRepository> projectRepositoryProvider;

  private final Provider<TaskRepository> taskRepositoryProvider;

  public ProjectDetailViewModel_Factory(Provider<ProjectRepository> projectRepositoryProvider,
      Provider<TaskRepository> taskRepositoryProvider) {
    this.projectRepositoryProvider = projectRepositoryProvider;
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public ProjectDetailViewModel get() {
    return newInstance(projectRepositoryProvider.get(), taskRepositoryProvider.get());
  }

  public static ProjectDetailViewModel_Factory create(
      Provider<ProjectRepository> projectRepositoryProvider,
      Provider<TaskRepository> taskRepositoryProvider) {
    return new ProjectDetailViewModel_Factory(projectRepositoryProvider, taskRepositoryProvider);
  }

  public static ProjectDetailViewModel newInstance(ProjectRepository projectRepository,
      TaskRepository taskRepository) {
    return new ProjectDetailViewModel(projectRepository, taskRepository);
  }
}
