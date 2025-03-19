package com.mlk.taskmanager.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\u0016J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\u0016J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\u0016J\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/mlk/taskmanager/data/repository/RoutineRepositoryImpl;", "Lcom/mlk/taskmanager/data/repository/RoutineRepository;", "routineDao", "Lcom/mlk/taskmanager/data/dao/RoutineDao;", "(Lcom/mlk/taskmanager/data/dao/RoutineDao;)V", "deleteRoutine", "", "routine", "Lcom/mlk/taskmanager/data/model/Routine;", "(Lcom/mlk/taskmanager/data/model/Routine;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveRoutines", "Lkotlinx/coroutines/flow/Flow;", "", "getAllRoutines", "getLocationBasedRoutines", "getRoutineById", "id", "", "getRoutinesForDay", "dayOfWeek", "Ljava/time/DayOfWeek;", "insertRoutine", "updateRoutine", "app_debug"})
public final class RoutineRepositoryImpl implements com.mlk.taskmanager.data.repository.RoutineRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.dao.RoutineDao routineDao = null;
    
    @javax.inject.Inject()
    public RoutineRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.dao.RoutineDao routineDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getAllRoutines() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getActiveRoutines() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getLocationBasedRoutines() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getRoutinesForDay(@org.jetbrains.annotations.NotNull()
    java.time.DayOfWeek dayOfWeek) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.mlk.taskmanager.data.model.Routine> getRoutineById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}