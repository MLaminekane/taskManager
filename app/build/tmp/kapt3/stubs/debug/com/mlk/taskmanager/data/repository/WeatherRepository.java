package com.mlk.taskmanager.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/mlk/taskmanager/data/repository/WeatherRepository;", "", "weatherApiService", "Lcom/mlk/taskmanager/data/api/WeatherApiService;", "context", "Landroid/content/Context;", "(Lcom/mlk/taskmanager/data/api/WeatherApiService;Landroid/content/Context;)V", "apiKey", "", "getApiKey", "()Ljava/lang/String;", "apiKey$delegate", "Lkotlin/Lazy;", "getCurrentWeather", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Result;", "Lcom/mlk/taskmanager/data/model/WeatherResponse;", "latitude", "", "longitude", "app_debug"})
public final class WeatherRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.api.WeatherApiService weatherApiService = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy apiKey$delegate = null;
    
    @javax.inject.Inject()
    public WeatherRepository(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.api.WeatherApiService weatherApiService, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final java.lang.String getApiKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<com.mlk.taskmanager.data.model.WeatherResponse>> getCurrentWeather(double latitude, double longitude) {
        return null;
    }
}