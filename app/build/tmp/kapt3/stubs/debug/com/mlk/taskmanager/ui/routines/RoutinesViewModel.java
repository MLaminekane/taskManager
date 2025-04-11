package com.mlk.taskmanager.ui.routines;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006Ja\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0002\u0010\u001eJ\u0006\u0010\u001f\u001a\u00020\u000fJ\u000e\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020\u000fH\u0002J\u000e\u0010$\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"J\u000e\u0010%\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"J\u000e\u0010&\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006'"}, d2 = {"Lcom/mlk/taskmanager/ui/routines/RoutinesViewModel;", "Landroidx/lifecycle/ViewModel;", "routineRepository", "Lcom/mlk/taskmanager/data/repository/RoutineRepository;", "calendarSyncService", "Lcom/mlk/taskmanager/service/CalendarSyncService;", "(Lcom/mlk/taskmanager/data/repository/RoutineRepository;Lcom/mlk/taskmanager/service/CalendarSyncService;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/routines/RoutinesUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addRoutine", "", "title", "", "description", "time", "Ljava/time/LocalTime;", "repeatDays", "", "Ljava/time/DayOfWeek;", "category", "latitude", "", "longitude", "locationRadius", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/time/LocalTime;Ljava/util/List;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;)V", "clearError", "deleteRoutine", "routine", "Lcom/mlk/taskmanager/data/model/Routine;", "loadRoutines", "toggleCalendarSync", "toggleRoutineEnabled", "updateRoutine", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RoutinesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.RoutineRepository routineRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.CalendarSyncService calendarSyncService = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.routines.RoutinesUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.routines.RoutinesUiState> uiState = null;
    
    @javax.inject.Inject()
    public RoutinesViewModel(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.RoutineRepository routineRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.CalendarSyncService calendarSyncService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.routines.RoutinesUiState> getUiState() {
        return null;
    }
    
    private final void loadRoutines() {
    }
    
    public final void addRoutine(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.time.LocalTime time, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.time.DayOfWeek> repeatDays, @org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Float locationRadius) {
    }
    
    public final void updateRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine) {
    }
    
    public final void toggleRoutineEnabled(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine) {
    }
    
    public final void deleteRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine) {
    }
    
    public final void toggleCalendarSync(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine) {
    }
    
    public final void clearError() {
    }
}