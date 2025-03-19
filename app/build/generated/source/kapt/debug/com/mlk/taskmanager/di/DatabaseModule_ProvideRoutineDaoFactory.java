package com.mlk.taskmanager.di;

import com.mlk.taskmanager.data.dao.RoutineDao;
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
public final class DatabaseModule_ProvideRoutineDaoFactory implements Factory<RoutineDao> {
  private final Provider<TaskDatabase> databaseProvider;

  public DatabaseModule_ProvideRoutineDaoFactory(Provider<TaskDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RoutineDao get() {
    return provideRoutineDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRoutineDaoFactory create(
      Provider<TaskDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRoutineDaoFactory(databaseProvider);
  }

  public static RoutineDao provideRoutineDao(TaskDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRoutineDao(database));
  }
}
