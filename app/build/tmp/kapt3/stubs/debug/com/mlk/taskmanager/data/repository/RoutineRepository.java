package com.mlk.taskmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010\r\u001a\u00020\u000eH&J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0014"}, d2 = {"Lcom/mlk/taskmanager/data/repository/RoutineRepository;", "", "deleteRoutine", "", "routine", "Lcom/mlk/taskmanager/data/model/Routine;", "(Lcom/mlk/taskmanager/data/model/Routine;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveRoutines", "Lkotlinx/coroutines/flow/Flow;", "", "getAllRoutines", "getLocationBasedRoutines", "getRoutineById", "id", "", "getRoutinesForDay", "dayOfWeek", "Ljava/time/DayOfWeek;", "insertRoutine", "updateRoutine", "app_debug"})
public abstract interface RoutineRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getAllRoutines();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getActiveRoutines();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getLocationBasedRoutines();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getRoutinesForDay(@org.jetbrains.annotations.NotNull()
    java.time.DayOfWeek dayOfWeek);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.mlk.taskmanager.data.model.Routine> getRoutineById(long id);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}