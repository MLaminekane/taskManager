package com.mlk.taskmanager.ui.steps;

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
public final class StepCounterViewModel_Factory implements Factory<StepCounterViewModel> {
  private final Provider<Context> contextProvider;

  public StepCounterViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StepCounterViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static StepCounterViewModel_Factory create(Provider<Context> contextProvider) {
    return new StepCounterViewModel_Factory(contextProvider);
  }

  public static StepCounterViewModel newInstance(Context context) {
    return new StepCounterViewModel(context);
  }
}
