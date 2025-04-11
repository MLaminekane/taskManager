package com.mlk.taskmanager.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/mlk/taskmanager/data/TaskManagerDatabase;", "Landroidx/room/RoomDatabase;", "()V", "projectDao", "Lcom/mlk/taskmanager/data/dao/ProjectDao;", "routineDao", "Lcom/mlk/taskmanager/data/dao/RoutineDao;", "taskDao", "Lcom/mlk/taskmanager/data/dao/TaskDao;", "userDao", "Lcom/mlk/taskmanager/data/dao/UserDao;", "app_debug"})
@androidx.room.Database(entities = {com.mlk.taskmanager.data.model.Task.class, com.mlk.taskmanager.data.model.Routine.class, com.mlk.taskmanager.data.model.Project.class, com.mlk.taskmanager.data.model.User.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.mlk.taskmanager.data.Converters.class})
public abstract class TaskManagerDatabase extends androidx.room.RoomDatabase {
    
    public TaskManagerDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.TaskDao taskDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.RoutineDao routineDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.ProjectDao projectDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.UserDao userDao();
}