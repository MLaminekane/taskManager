package com.mlk.taskmanager.di;

import com.mlk.taskmanager.data.dao.ProjectDao;
import com.mlk.taskmanager.data.local.TaskDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideProjectDaoFactory implements Factory<ProjectDao> {
  private final Provider<TaskDatabase> databaseProvider;

  public DatabaseModule_ProvideProjectDaoFactory(Provider<TaskDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ProjectDao get() {
    return provideProjectDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideProjectDaoFactory create(
      Provider<TaskDatabase> databaseProvider) {
    return new DatabaseModule_ProvideProjectDaoFactory(databaseProvider);
  }

  public static ProjectDao provideProjectDao(TaskDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideProjectDao(database));
  }
}
