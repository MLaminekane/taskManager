package com.mlk.taskmanager.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\'\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0014"}, d2 = {"Lcom/mlk/taskmanager/data/local/TaskDatabase;", "Landroidx/room/RoomDatabase;", "()V", "projectDao", "Lcom/mlk/taskmanager/data/dao/ProjectDao;", "getProjectDao", "()Lcom/mlk/taskmanager/data/dao/ProjectDao;", "routineDao", "Lcom/mlk/taskmanager/data/dao/RoutineDao;", "getRoutineDao", "()Lcom/mlk/taskmanager/data/dao/RoutineDao;", "taskDao", "Lcom/mlk/taskmanager/data/dao/TaskDao;", "getTaskDao", "()Lcom/mlk/taskmanager/data/dao/TaskDao;", "userDao", "Lcom/mlk/taskmanager/data/dao/UserDao;", "getUserDao", "()Lcom/mlk/taskmanager/data/dao/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.mlk.taskmanager.data.model.Task.class, com.mlk.taskmanager.data.model.Routine.class, com.mlk.taskmanager.data.model.Project.class, com.mlk.taskmanager.data.model.User.class}, version = 5, exportSchema = false)
@androidx.room.TypeConverters(value = {com.mlk.taskmanager.data.util.Converters.class})
public abstract class TaskDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_2_3 = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_3_4 = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_4_5 = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.data.local.TaskDatabase.Companion Companion = null;
    
    public TaskDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.TaskDao getTaskDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.RoutineDao getRoutineDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.ProjectDao getProjectDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.mlk.taskmanager.data.dao.UserDao getUserDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/mlk/taskmanager/data/local/TaskDatabase$Companion;", "", "()V", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "MIGRATION_2_3", "getMIGRATION_2_3", "MIGRATION_3_4", "getMIGRATION_3_4", "MIGRATION_4_5", "getMIGRATION_4_5", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_1_2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_2_3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_3_4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_4_5() {
            return null;
        }
    }
}