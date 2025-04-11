package com.mlk.taskmanager.di;

import com.mlk.taskmanager.data.dao.UserDao;
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
public final class DatabaseModule_ProvideUserDaoFactory implements Factory<UserDao> {
  private final Provider<TaskDatabase> databaseProvider;

  public DatabaseModule_ProvideUserDaoFactory(Provider<TaskDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserDao get() {
    return provideUserDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideUserDaoFactory create(
      Provider<TaskDatabase> databaseProvider) {
    return new DatabaseModule_ProvideUserDaoFactory(databaseProvider);
  }

  public static UserDao provideUserDao(TaskDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideUserDao(database));
  }
}
