package com.mlk.taskmanager.ui.home;

/**
 * ViewModel pour l'écran d'accueil
 * Gère les données des tâches, routines, projets et informations météo
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aJ\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002J\u0006\u0010!\u001a\u00020\u0017J\b\u0010\"\u001a\u00020\u0017H\u0002J\u0006\u0010#\u001a\u00020\u0017J\b\u0010$\u001a\u00020\u0017H\u0007J\u000e\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\'J\u0006\u0010(\u001a\u00020\u0017J\u0006\u0010)\u001a\u00020\u0017R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/mlk/taskmanager/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "routineRepository", "Lcom/mlk/taskmanager/data/repository/RoutineRepository;", "projectRepository", "Lcom/mlk/taskmanager/data/repository/ProjectRepository;", "weatherRepository", "Lcom/mlk/taskmanager/data/repository/WeatherRepository;", "context", "Landroid/content/Context;", "(Lcom/mlk/taskmanager/data/repository/TaskRepository;Lcom/mlk/taskmanager/data/repository/RoutineRepository;Lcom/mlk/taskmanager/data/repository/ProjectRepository;Lcom/mlk/taskmanager/data/repository/WeatherRepository;Landroid/content/Context;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/home/HomeUiState;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "createProject", "name", "", "description", "icon", "fetchWeatherForLocation", "latitude", "", "longitude", "hideCreateProjectDialog", "initLocationClient", "loadData", "loadWeatherData", "setTaskFilter", "filter", "Lcom/mlk/taskmanager/ui/home/TaskFilter;", "showCreateProjectDialog", "toggleWeatherModal", "Companion", "app_debug"})
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
    private static final java.lang.String TAG = "HomeViewModel";
    private static final double DEFAULT_LATITUDE = 48.8566;
    private static final double DEFAULT_LONGITUDE = 2.3522;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.home.HomeUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.home.HomeUiState> uiState = null;
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient;
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.ui.home.HomeViewModel.Companion Companion = null;
    
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
    
    /**
     * Initialise le client de localisation
     */
    private final void initLocationClient() {
    }
    
    /**
     * Charge toutes les données nécessaires à l'écran d'accueil
     * (projets, tâches, routines) et initialise l'état de l'UI
     */
    public final void loadData() {
    }
    
    /**
     * Définit le filtre de tâches actif
     * @param filter Le filtre à appliquer
     */
    public final void setTaskFilter(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.TaskFilter filter) {
    }
    
    /**
     * Efface le message d'erreur
     */
    public final void clearError() {
    }
    
    /**
     * Affiche le dialogue de création de projet
     */
    public final void showCreateProjectDialog() {
    }
    
    /**
     * Masque le dialogue de création de projet
     */
    public final void hideCreateProjectDialog() {
    }
    
    /**
     * Crée un nouveau projet
     * @param name Nom du projet
     * @param description Description du projet
     * @param icon Icône du projet
     */
    public final void createProject(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String icon) {
    }
    
    /**
     * Charge les données météo basées sur la localisation de l'utilisateur
     */
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    public final void loadWeatherData() {
    }
    
    /**
     * Récupère les données météo pour une localisation donnée
     * @param latitude Latitude
     * @param longitude Longitude
     */
    private final void fetchWeatherForLocation(double latitude, double longitude) {
    }
    
    /**
     * Bascule la visibilité du modal météo
     */
    public final void toggleWeatherModal() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/mlk/taskmanager/ui/home/HomeViewModel$Companion;", "", "()V", "DEFAULT_LATITUDE", "", "DEFAULT_LONGITUDE", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}