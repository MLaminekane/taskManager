package com.mlk.taskmanager.ui.pomodoro;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class PomodoroViewModel_Factory implements Factory<PomodoroViewModel> {
  private final Provider<Context> contextProvider;

  public PomodoroViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PomodoroViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static PomodoroViewModel_Factory create(Provider<Context> contextProvider) {
    return new PomodoroViewModel_Factory(contextProvider);
  }

  public static PomodoroViewModel newInstance(Context context) {
    return new PomodoroViewModel(context);
  }
}
