package com.mlk.taskmanager.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u0011\u001a\u00020\u0012H\'J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0015"}, d2 = {"Lcom/mlk/taskmanager/data/dao/RoutineDao;", "", "deleteRoutine", "", "routine", "Lcom/mlk/taskmanager/data/model/Routine;", "(Lcom/mlk/taskmanager/data/model/Routine;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveRoutines", "Lkotlinx/coroutines/flow/Flow;", "", "getAllRoutines", "getLocationBasedRoutines", "getRoutineById", "routineId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoutinesForDay", "dayOfWeek", "Ljava/time/DayOfWeek;", "insertRoutine", "updateRoutine", "app_debug"})
@androidx.room.Dao()
public abstract interface RoutineDao {
    
    @androidx.room.Query(value = "SELECT * FROM routines ORDER BY time ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getAllRoutines();
    
    @androidx.room.Query(value = "SELECT * FROM routines WHERE isEnabled = 1 ORDER BY time ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getActiveRoutines();
    
    @androidx.room.Query(value = "SELECT * FROM routines WHERE id = :routineId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRoutineById(long routineId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.mlk.taskmanager.data.model.Routine> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM routines \n        WHERE isEnabled = 1 \n        AND latitude IS NOT NULL \n        AND longitude IS NOT NULL\n        ORDER BY time ASC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getLocationBasedRoutines();
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRoutine(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM routines WHERE :dayOfWeek IN (repeatDays) AND isEnabled = 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.mlk.taskmanager.data.model.Routine>> getRoutinesForDay(@org.jetbrains.annotations.NotNull()
    java.time.DayOfWeek dayOfWeek);
}