package com.mlk.taskmanager.ui.routines;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0006\u0010\u0017\u001a\u00020\u0015J\u0006\u0010\u0018\u001a\u00020\u0015J4\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006$"}, d2 = {"Lcom/mlk/taskmanager/ui/routines/RoutineDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "routineRepository", "Lcom/mlk/taskmanager/data/repository/RoutineRepository;", "calendarSyncService", "Lcom/mlk/taskmanager/service/CalendarSyncService;", "settingsRepository", "Lcom/mlk/taskmanager/data/repository/SettingsRepository;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/mlk/taskmanager/data/repository/RoutineRepository;Lcom/mlk/taskmanager/service/CalendarSyncService;Lcom/mlk/taskmanager/data/repository/SettingsRepository;Landroidx/lifecycle/SavedStateHandle;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/routines/RoutineDetailUiState;", "routineId", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteRoutine", "", "loadRoutine", "toggleCalendarSync", "toggleEnabled", "updateRoutine", "title", "", "description", "time", "Ljava/time/LocalTime;", "repeatDays", "", "Ljava/time/DayOfWeek;", "isEnabled", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RoutineDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.RoutineRepository routineRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.CalendarSyncService calendarSyncService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.SettingsRepository settingsRepository = null;
    private final long routineId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.routines.RoutineDetailUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.routines.RoutineDetailUiState> uiState = null;
    
    @javax.inject.Inject()
    public RoutineDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.RoutineRepository routineRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.CalendarSyncService calendarSyncService, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.routines.RoutineDetailUiState> getUiState() {
        return null;
    }
    
    private final void loadRoutine() {
    }
    
    public final void toggleCalendarSync() {
    }
    
    public final void updateRoutine(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.time.LocalTime time, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.time.DayOfWeek> repeatDays, boolean isEnabled) {
    }
    
    public final void deleteRoutine() {
    }
    
    public final void toggleEnabled() {
    }
}