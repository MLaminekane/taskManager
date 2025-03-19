package com.mlk.taskmanager.data.repository;

import com.mlk.taskmanager.data.dao.TaskDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TaskRepositoryImpl_Factory implements Factory<TaskRepositoryImpl> {
  private final Provider<TaskDao> taskDaoProvider;

  private final Provider<ProjectRepository> projectRepositoryProvider;

  public TaskRepositoryImpl_Factory(Provider<TaskDao> taskDaoProvider,
      Provider<ProjectRepository> projectRepositoryProvider) {
    this.taskDaoProvider = taskDaoProvider;
    this.projectRepositoryProvider = projectRepositoryProvider;
  }

  @Override
  public TaskRepositoryImpl get() {
    return newInstance(taskDaoProvider.get(), projectRepositoryProvider.get());
  }

  public static TaskRepositoryImpl_Factory create(Provider<TaskDao> taskDaoProvider,
      Provider<ProjectRepository> projectRepositoryProvider) {
    return new TaskRepositoryImpl_Factory(taskDaoProvider, projectRepositoryProvider);
  }

  public static TaskRepositoryImpl newInstance(TaskDao taskDao,
      ProjectRepository projectRepository) {
    return new TaskRepositoryImpl(taskDao, projectRepository);
  }
}
