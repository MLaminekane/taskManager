package com.mlk.taskmanager.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/mlk/taskmanager/data/local/TaskDatabase;", "Landroidx/room/RoomDatabase;", "()V", "taskDao", "Lcom/mlk/taskmanager/data/dao/TaskDao;", "getTaskDao", "()Lcom/mlk/taskmanager/data/dao/TaskDao;", "app_debug"})
@androidx.room.Database(entities = {com.mlk.taskmanager.data.model.Task.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.mlk.taskmanager.data.util.Converters.class})
public abstract class TaskDatabase extends androidx.room.RoomDatabase {
    
    public TaskDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.TaskDao getTaskDao();
}