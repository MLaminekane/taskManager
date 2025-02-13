package com.mlk.taskmanager.ui.calendar;

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
public final class CalendarViewModel_Factory implements Factory<CalendarViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public CalendarViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public CalendarViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static CalendarViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider) {
    return new CalendarViewModel_Factory(taskRepositoryProvider);
  }

  public static CalendarViewModel newInstance(TaskRepository taskRepository) {
    return new CalendarViewModel(taskRepository);
  }
}
