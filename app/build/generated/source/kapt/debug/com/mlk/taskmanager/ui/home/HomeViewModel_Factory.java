package com.mlk.taskmanager.ui.home;

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

  public HomeViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider) {
    return new HomeViewModel_Factory(taskRepositoryProvider);
  }

  public static HomeViewModel newInstance(TaskRepository taskRepository) {
    return new HomeViewModel(taskRepository);
  }
}
