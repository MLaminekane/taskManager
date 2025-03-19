package com.mlk.taskmanager.data.repository;

import android.content.Context;
import com.mlk.taskmanager.data.api.WeatherApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class WeatherRepository_Factory implements Factory<WeatherRepository> {
  private final Provider<WeatherApiService> weatherApiServiceProvider;

  private final Provider<Context> contextProvider;

  public WeatherRepository_Factory(Provider<WeatherApiService> weatherApiServiceProvider,
      Provider<Context> contextProvider) {
    this.weatherApiServiceProvider = weatherApiServiceProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public WeatherRepository get() {
    return newInstance(weatherApiServiceProvider.get(), contextProvider.get());
  }

  public static WeatherRepository_Factory create(
      Provider<WeatherApiService> weatherApiServiceProvider, Provider<Context> contextProvider) {
    return new WeatherRepository_Factory(weatherApiServiceProvider, contextProvider);
  }

  public static WeatherRepository newInstance(WeatherApiService weatherApiService,
      Context context) {
    return new WeatherRepository(weatherApiService, context);
  }
}
