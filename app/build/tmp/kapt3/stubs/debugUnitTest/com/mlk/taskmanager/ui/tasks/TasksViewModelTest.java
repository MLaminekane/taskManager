package com.mlk.taskmanager.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013H\u0007J\f\u0010\u0014\u001a\u00060\u0012j\u0002`\u0013H\u0007J\f\u0010\u0015\u001a\u00060\u0012j\u0002`\u0013H\u0007J\f\u0010\u0016\u001a\u00060\u0012j\u0002`\u0013H\u0007J\f\u0010\u0017\u001a\u00060\u0012j\u0002`\u0013H\u0007J\b\u0010\u0018\u001a\u00020\u0012H\u0007J\b\u0010\u0019\u001a\u00020\u0012H\u0007J\f\u0010\u001a\u001a\u00060\u0012j\u0002`\u0013H\u0007J\f\u0010\u001b\u001a\u00060\u0012j\u0002`\u0013H\u0007J\f\u0010\u001c\u001a\u00060\u0012j\u0002`\u0013H\u0007J\f\u0010\u001d\u001a\u00060\u0012j\u0002`\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/mlk/taskmanager/ui/tasks/TasksViewModelTest;", "", "()V", "locationReminderService", "Lcom/mlk/taskmanager/service/LocationReminderService;", "notificationManager", "Lcom/mlk/taskmanager/service/NotificationManager;", "taskRepository", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "tasksFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/mlk/taskmanager/data/model/Task;", "testDispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "viewModel", "Lcom/mlk/taskmanager/ui/tasks/TasksViewModel;", "addTask should call repository and notificationManager", "", "Lkotlinx/coroutines/test/TestResult;", "deleteTask should call repository", "initial state should have empty tasks and isLoading true", "setSearchQuery should filter tasks", "setSortOption should sort filtered tasks", "setup", "tearDown", "togglePriorityFilter should update filtered tasks", "toggleShowCompletedTasks should update filtered tasks", "toggleTaskCompletion should update task completion status", "uiState should reflect loaded tasks", "app_debugUnitTest"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class TasksViewModelTest {
    private com.mlk.taskmanager.data.repository.TaskRepository taskRepository;
    private com.mlk.taskmanager.service.LocationReminderService locationReminderService;
    private com.mlk.taskmanager.service.NotificationManager notificationManager;
    private com.mlk.taskmanager.ui.tasks.TasksViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.test.TestDispatcher testDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.mlk.taskmanager.data.model.Task>> tasksFlow = null;
    
    public TasksViewModelTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    @org.junit.After()
    public final void tearDown() {
    }
}