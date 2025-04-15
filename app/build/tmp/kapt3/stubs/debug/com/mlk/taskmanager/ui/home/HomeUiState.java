package com.mlk.taskmanager.ui.home;

/**
 * État de l'interface utilisateur pour l'écran d'accueil
 *
 * @param assignedTasks Nombre de tâches assignées (non complétées)
 * @param completedTasks Nombre de tâches complétées
 * @param todayTasks Liste des tâches du jour
 * @param upcomingTasks Liste des tâches à venir
 * @param todayRoutines Liste des routines du jour
 * @param projects Liste des projets
 * @param selectedFilter Filtre de tâches sélectionné
 * @param isLoading Indique si les données sont en cours de chargement
 * @param error Message d'erreur éventuel
 * @param showCreateProjectDialog Afficher le dialogue de création de projet
 * @param weatherData Données météo
 * @param weatherLoading Indique si les données météo sont en cours de chargement
 * @param weatherError Message d'erreur pour les données météo
 * @param weatherModalVisible Afficher le modal météo
 * @param currentUser Nom de l'utilisateur actuel
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b.\b\u0087\b\u0018\u00002\u00020\u0001B\u00b9\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u001aJ\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0010H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0015H\u00c6\u0003J\t\u00102\u001a\u00020\u0010H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\t\u00104\u001a\u00020\u0010H\u00c6\u0003J\t\u00105\u001a\u00020\u0012H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\n0\u0006H\u00c6\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0\u0006H\u00c6\u0003J\t\u0010;\u001a\u00020\u000eH\u00c6\u0003J\t\u0010<\u001a\u00020\u0010H\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\u00bd\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00102\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u0012H\u00c6\u0001J\u0013\u0010?\u001a\u00020\u00102\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010A\u001a\u00020\u0003H\u00d6\u0001J\t\u0010B\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0019\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010!R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0013\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010#R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0011\u0010\u0018\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010!\u00a8\u0006C"}, d2 = {"Lcom/mlk/taskmanager/ui/home/HomeUiState;", "", "assignedTasks", "", "completedTasks", "todayTasks", "", "Lcom/mlk/taskmanager/data/model/Task;", "upcomingTasks", "todayRoutines", "Lcom/mlk/taskmanager/data/model/Routine;", "projects", "Lcom/mlk/taskmanager/data/model/Project;", "selectedFilter", "Lcom/mlk/taskmanager/ui/home/TaskFilter;", "isLoading", "", "error", "", "showCreateProjectDialog", "weatherData", "Lcom/mlk/taskmanager/data/model/WeatherResponse;", "weatherLoading", "weatherError", "weatherModalVisible", "currentUser", "(IILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/mlk/taskmanager/ui/home/TaskFilter;ZLjava/lang/String;ZLcom/mlk/taskmanager/data/model/WeatherResponse;ZLjava/lang/String;ZLjava/lang/String;)V", "getAssignedTasks", "()I", "getCompletedTasks", "getCurrentUser", "()Ljava/lang/String;", "getError", "()Z", "getProjects", "()Ljava/util/List;", "getSelectedFilter", "()Lcom/mlk/taskmanager/ui/home/TaskFilter;", "getShowCreateProjectDialog", "getTodayRoutines", "getTodayTasks", "getUpcomingTasks", "getWeatherData", "()Lcom/mlk/taskmanager/data/model/WeatherResponse;", "getWeatherError", "getWeatherLoading", "getWeatherModalVisible", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class HomeUiState {
    private final int assignedTasks = 0;
    private final int completedTasks = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.data.model.Task> todayTasks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.data.model.Task> upcomingTasks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.data.model.Routine> todayRoutines = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.data.model.Project> projects = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.ui.home.TaskFilter selectedFilter = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean showCreateProjectDialog = false;
    @org.jetbrains.annotations.Nullable()
    private final com.mlk.taskmanager.data.model.WeatherResponse weatherData = null;
    private final boolean weatherLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String weatherError = null;
    private final boolean weatherModalVisible = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currentUser = null;
    
    public HomeUiState(int assignedTasks, int completedTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> todayTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> upcomingTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Routine> todayRoutines, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Project> projects, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.TaskFilter selectedFilter, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showCreateProjectDialog, @org.jetbrains.annotations.Nullable()
    com.mlk.taskmanager.data.model.WeatherResponse weatherData, boolean weatherLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String weatherError, boolean weatherModalVisible, @org.jetbrains.annotations.NotNull()
    java.lang.String currentUser) {
        super();
    }
    
    public final int getAssignedTasks() {
        return 0;
    }
    
    public final int getCompletedTasks() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> getTodayTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> getUpcomingTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Routine> getTodayRoutines() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Project> getProjects() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.home.TaskFilter getSelectedFilter() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final boolean getShowCreateProjectDialog() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.mlk.taskmanager.data.model.WeatherResponse getWeatherData() {
        return null;
    }
    
    public final boolean getWeatherLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getWeatherError() {
        return null;
    }
    
    public final boolean getWeatherModalVisible() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUser() {
        return null;
    }
    
    public HomeUiState() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.mlk.taskmanager.data.model.WeatherResponse component11() {
        return null;
    }
    
    public final boolean component12() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    public final boolean component14() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Routine> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Project> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.home.TaskFilter component7() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.home.HomeUiState copy(int assignedTasks, int completedTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> todayTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> upcomingTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Routine> todayRoutines, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Project> projects, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.TaskFilter selectedFilter, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showCreateProjectDialog, @org.jetbrains.annotations.Nullable()
    com.mlk.taskmanager.data.model.WeatherResponse weatherData, boolean weatherLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String weatherError, boolean weatherModalVisible, @org.jetbrains.annotations.NotNull()
    java.lang.String currentUser) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}