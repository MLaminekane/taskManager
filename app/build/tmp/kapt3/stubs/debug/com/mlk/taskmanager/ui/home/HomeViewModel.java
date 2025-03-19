package com.mlk.taskmanager.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aJ\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002J\u0006\u0010!\u001a\u00020\u0017J\b\u0010\"\u001a\u00020\u0017H\u0002J\b\u0010#\u001a\u00020\u0017H\u0007J\u000e\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&J\u0006\u0010\'\u001a\u00020\u0017J\u0006\u0010(\u001a\u00020\u0017R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/mlk/taskmanager/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "routineRepository", "Lcom/mlk/taskmanager/data/repository/RoutineRepository;", "projectRepository", "Lcom/mlk/taskmanager/data/repository/ProjectRepository;", "weatherRepository", "Lcom/mlk/taskmanager/data/repository/WeatherRepository;", "context", "Landroid/content/Context;", "(Lcom/mlk/taskmanager/data/repository/TaskRepository;Lcom/mlk/taskmanager/data/repository/RoutineRepository;Lcom/mlk/taskmanager/data/repository/ProjectRepository;Lcom/mlk/taskmanager/data/repository/WeatherRepository;Landroid/content/Context;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/home/HomeUiState;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "createProject", "name", "", "description", "icon", "fetchWeatherForLocation", "latitude", "", "longitude", "hideCreateProjectDialog", "loadData", "loadWeatherData", "setTaskFilter", "filter", "Lcom/mlk/taskmanager/ui/home/TaskFilter;", "showCreateProjectDialog", "toggleWeatherModal", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.TaskRepository taskRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.RoutineRepository routineRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.ProjectRepository projectRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.WeatherRepository weatherRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.home.HomeUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.home.HomeUiState> uiState = null;
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.TaskRepository taskRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.RoutineRepository routineRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.ProjectRepository projectRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.WeatherRepository weatherRepository, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.home.HomeUiState> getUiState() {
        return null;
    }
    
    private final void loadData() {
    }
    
    public final void setTaskFilter(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.TaskFilter filter) {
    }
    
    public final void clearError() {
    }
    
    public final void showCreateProjectDialog() {
    }
    
    public final void hideCreateProjectDialog() {
    }
    
    public final void createProject(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String icon) {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    public final void loadWeatherData() {
    }
    
    private final void fetchWeatherForLocation(double latitude, double longitude) {
    }
    
    public final void toggleWeatherModal() {
    }
}