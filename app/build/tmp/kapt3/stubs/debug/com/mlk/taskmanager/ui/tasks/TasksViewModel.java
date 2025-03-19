package com.mlk.taskmanager.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJg\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u00a2\u0006\u0002\u0010!J$\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010&\u001a\u00020\u000bH\u0002J\u0006\u0010\'\u001a\u00020\u0011J\u0006\u0010(\u001a\u00020\u0011J\u000e\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020$J\b\u0010+\u001a\u00020\u0011H\u0002J\u000e\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0013J\u000e\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u00020\u0011J\u000e\u00102\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u00103\u001a\u00020\u0011J\u0006\u00104\u001a\u00020\u0011J\u000e\u00105\u001a\u00020\u00112\u0006\u0010*\u001a\u00020$J\b\u00106\u001a\u00020\u0011H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u00067"}, d2 = {"Lcom/mlk/taskmanager/ui/tasks/TasksViewModel;", "Landroidx/lifecycle/ViewModel;", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "locationReminderService", "Lcom/mlk/taskmanager/service/LocationReminderService;", "notificationManager", "Lcom/mlk/taskmanager/service/NotificationManager;", "(Lcom/mlk/taskmanager/data/repository/TaskRepository;Lcom/mlk/taskmanager/service/LocationReminderService;Lcom/mlk/taskmanager/service/NotificationManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/tasks/TasksUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addTask", "", "title", "", "description", "dueDateTime", "Ljava/time/LocalDateTime;", "priority", "Lcom/mlk/taskmanager/data/model/Priority;", "category", "latitude", "", "longitude", "locationRadius", "", "projectId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/time/LocalDateTime;Lcom/mlk/taskmanager/data/model/Priority;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Long;)V", "applyFilters", "", "Lcom/mlk/taskmanager/data/model/Task;", "tasks", "state", "clearError", "delayedRefresh", "deleteTask", "task", "loadTasks", "setSearchQuery", "query", "setSortOption", "option", "Lcom/mlk/taskmanager/ui/tasks/SortOption;", "toggleFilterDialog", "togglePriorityFilter", "toggleSearchActive", "toggleShowCompletedTasks", "toggleTaskCompletion", "updateFilteredTasks", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TasksViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.TaskRepository taskRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.LocationReminderService locationReminderService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.NotificationManager notificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.tasks.TasksUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.tasks.TasksUiState> uiState = null;
    
    @javax.inject.Inject()
    public TasksViewModel(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.TaskRepository taskRepository, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.LocationReminderService locationReminderService, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.NotificationManager notificationManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.tasks.TasksUiState> getUiState() {
        return null;
    }
    
    private final void loadTasks() {
    }
    
    private final java.util.List<com.mlk.taskmanager.data.model.Task> applyFilters(java.util.List<com.mlk.taskmanager.data.model.Task> tasks, com.mlk.taskmanager.ui.tasks.TasksUiState state) {
        return null;
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void toggleSearchActive() {
    }
    
    public final void toggleFilterDialog() {
    }
    
    public final void togglePriorityFilter(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Priority priority) {
    }
    
    public final void toggleShowCompletedTasks() {
    }
    
    public final void setSortOption(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.tasks.SortOption option) {
    }
    
    private final void updateFilteredTasks() {
    }
    
    public final void addTask(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime dueDateTime, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Priority priority, @org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Float locationRadius, @org.jetbrains.annotations.Nullable()
    java.lang.Long projectId) {
    }
    
    public final void toggleTaskCompletion(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task) {
    }
    
    public final void deleteTask(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task) {
    }
    
    public final void clearError() {
    }
    
    public final void delayedRefresh() {
    }
}