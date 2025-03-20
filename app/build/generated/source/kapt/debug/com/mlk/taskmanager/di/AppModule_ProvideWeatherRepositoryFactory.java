package com.mlk.taskmanager.di;

import android.content.Context;
import com.mlk.taskmanager.data.api.WeatherApiService;
import com.mlk.taskmanager.data.repository.WeatherRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideWeatherRepositoryFactory implements Factory<WeatherRepository> {
  private final Provider<WeatherApiService> weatherApiServiceProvider;

  private final Provider<Context> contextProvider;

  public AppModule_ProvideWeatherRepositoryFactory(
      Provider<WeatherApiService> weatherApiServiceProvider, Provider<Context> contextProvider) {
    this.weatherApiServiceProvider = weatherApiServiceProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public WeatherRepository get() {
    return provideWeatherRepository(weatherApiServiceProvider.get(), contextProvider.get());
  }

  public static AppModule_ProvideWeatherRepositoryFactory create(
      Provider<WeatherApiService> weatherApiServiceProvider, Provider<Context> contextProvider) {
    return new AppModule_ProvideWeatherRepositoryFactory(weatherApiServiceProvider, contextProvider);
  }

  public static WeatherRepository provideWeatherRepository(WeatherApiService weatherApiService,
      Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideWeatherRepository(weatherApiService, context));
  }
}
