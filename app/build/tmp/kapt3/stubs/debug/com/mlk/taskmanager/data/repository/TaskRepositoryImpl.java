package com.mlk.taskmanager.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000fH\u0016J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000fH\u0016J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000fH\u0016J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0096@\u00a2\u0006\u0002\u0010\u001bJ$\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000f2\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u0019H\u0016J\u0016\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010 \u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/mlk/taskmanager/data/repository/TaskRepositoryImpl;", "Lcom/mlk/taskmanager/data/repository/TaskRepository;", "taskDao", "Lcom/mlk/taskmanager/data/dao/TaskDao;", "projectRepository", "Lcom/mlk/taskmanager/data/repository/ProjectRepository;", "(Lcom/mlk/taskmanager/data/dao/TaskDao;Lcom/mlk/taskmanager/data/repository/ProjectRepository;)V", "deleteCompletedTasks", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTask", "task", "Lcom/mlk/taskmanager/data/model/Task;", "(Lcom/mlk/taskmanager/data/model/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveTasks", "Lkotlinx/coroutines/flow/Flow;", "", "getAllTasks", "getLocationBasedTasks", "getTaskById", "taskId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksByDateRange", "startDateTime", "Ljava/time/LocalDateTime;", "endDateTime", "(Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksInTimeRange", "start", "end", "insertTask", "updateTask", "app_debug"})
public final class TaskRepositoryImpl implements com.mlk.taskmanager.data.repository.TaskRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.dao.TaskDao taskDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.ProjectRepository projectRepository = null;
    
    @javax.inject.Inject()
    public TaskRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.dao.TaskDao taskDao, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.ProjectRepository projectRepository) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getAllTasks() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getActiveTasks() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getLocationBasedTasks() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getTasksInTimeRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime start, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime end) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTaskById(long taskId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.mlk.taskmanager.data.model.Task> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertTask(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateTask(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteTask(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteCompletedTasks(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTasksByDateRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime startDateTime, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime endDateTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.mlk.taskmanager.data.model.Task>> $completion) {
        return null;
    }
}