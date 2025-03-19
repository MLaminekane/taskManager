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
public final class RoutineRepositoryImpl_Factory implements Factory<RoutineRepositoryImpl> {
  private final Provider<RoutineDao> routineDaoProvider;

  public RoutineRepositoryImpl_Factory(Provider<RoutineDao> routineDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
  }

  @Override
  public RoutineRepositoryImpl get() {
    return newInstance(routineDaoProvider.get());
  }

  public static RoutineRepositoryImpl_Factory create(Provider<RoutineDao> routineDaoProvider) {
    return new RoutineRepositoryImpl_Factory(routineDaoProvider);
  }

  public static RoutineRepositoryImpl newInstance(RoutineDao routineDao) {
    return new RoutineRepositoryImpl(routineDao);
  }
}
