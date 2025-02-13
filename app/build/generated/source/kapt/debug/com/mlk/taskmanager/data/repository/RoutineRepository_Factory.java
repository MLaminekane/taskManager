package com.mlk.taskmanager.data.repository;

import com.mlk.taskmanager.data.dao.RoutineDao;
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
public final class RoutineRepository_Factory implements Factory<RoutineRepository> {
  private final Provider<RoutineDao> routineDaoProvider;

  public RoutineRepository_Factory(Provider<RoutineDao> routineDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
  }

  @Override
  public RoutineRepository get() {
    return newInstance(routineDaoProvider.get());
  }

  public static RoutineRepository_Factory create(Provider<RoutineDao> routineDaoProvider) {
    return new RoutineRepository_Factory(routineDaoProvider);
  }

  public static RoutineRepository newInstance(RoutineDao routineDao) {
    return new RoutineRepository(routineDao);
  }
}
