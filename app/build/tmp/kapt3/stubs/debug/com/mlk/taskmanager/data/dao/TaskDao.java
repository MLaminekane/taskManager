package com.mlk.taskmanager.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0014H\'J\u0016\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u001c"}, d2 = {"Lcom/mlk/taskmanager/data/dao/TaskDao;", "", "deleteCompletedTasks", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTask", "task", "Lcom/mlk/taskmanager/data/model/Task;", "(Lcom/mlk/taskmanager/data/model/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveTasks", "Lkotlinx/coroutines/flow/Flow;", "", "getAllTasks", "getLocationBasedTasks", "getTaskById", "taskId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksByDateRange", "startDateTime", "Ljava/time/LocalDateTime;", "endDateTime", "(Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksInTimeRange", "start", "end", "insertTask", "updateTask", "app_debug"})
@androidx.room.Dao()
public abstract interface TaskDao {
    
    @androidx.room.Query(value = "SELECT * FROM tasks ORDER BY dueDateTime ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getAllTasks();
    
    @androidx.room.Query(value = "SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY dueDateTime ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getActiveTasks();
    
    @androidx.room.Query(value = "SELECT * FROM tasks WHERE id = :taskId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTaskById(long taskId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.mlk.taskmanager.data.model.Task> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM tasks \n        WHERE isCompleted = 0 \n        AND latitude IS NOT NULL \n        AND longitude IS NOT NULL\n        ORDER BY dueDateTime ASC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getLocationBasedTasks();
    
    @androidx.room.Query(value = "\n        SELECT * FROM tasks \n        WHERE isCompleted = 0 \n        AND dueDateTime BETWEEN :start AND :end\n        ORDER BY dueDateTime ASC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Task>> getTasksInTimeRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime start, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime end);
    
    @androidx.room.Query(value = "\n        SELECT * FROM tasks \n        WHERE dueDateTime BETWEEN :startDateTime AND :endDateTime \n        ORDER BY dueDateTime ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTasksByDateRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime startDateTime, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime endDateTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.mlk.taskmanager.data.model.Task>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTask(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTask(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTask(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM tasks WHERE isCompleted = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCompletedTasks(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}