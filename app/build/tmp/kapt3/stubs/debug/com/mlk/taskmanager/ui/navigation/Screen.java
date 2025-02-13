package com.mlk.taskmanager.ui.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\t\u0007\b\t\n\u000b\f\r\u000e\u000fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\t\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "AddRoutine", "AddTask", "Calendar", "Home", "RoutineDetail", "Settings", "TaskDetail", "Tasks", "Welcome", "Lcom/mlk/taskmanager/ui/navigation/Screen$AddRoutine;", "Lcom/mlk/taskmanager/ui/navigation/Screen$AddTask;", "Lcom/mlk/taskmanager/ui/navigation/Screen$Calendar;", "Lcom/mlk/taskmanager/ui/navigation/Screen$Home;", "Lcom/mlk/taskmanager/ui/navigation/Screen$RoutineDetail;", "Lcom/mlk/taskmanager/ui/navigation/Screen$Settings;", "Lcom/mlk/taskmanager/ui/navigation/Screen$TaskDetail;", "Lcom/mlk/taskmanager/ui/navigation/Screen$Tasks;", "Lcom/mlk/taskmanager/ui/navigation/Screen$Welcome;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$AddRoutine;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class AddRoutine extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.AddRoutine INSTANCE = null;
        
        private AddRoutine() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$AddTask;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class AddTask extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.AddTask INSTANCE = null;
        
        private AddTask() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$Calendar;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Calendar extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.Calendar INSTANCE = null;
        
        private Calendar() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$Home;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Home extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.Home INSTANCE = null;
        
        private Home() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$RoutineDetail;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "createRoute", "", "routineId", "", "app_debug"})
    public static final class RoutineDetail extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.RoutineDetail INSTANCE = null;
        
        private RoutineDetail() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createRoute(long routineId) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$Settings;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Settings extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.Settings INSTANCE = null;
        
        private Settings() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$TaskDetail;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "createRoute", "", "taskId", "", "app_debug"})
    public static final class TaskDetail extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.TaskDetail INSTANCE = null;
        
        private TaskDetail() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createRoute(long taskId) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$Tasks;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Tasks extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.Tasks INSTANCE = null;
        
        private Tasks() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/mlk/taskmanager/ui/navigation/Screen$Welcome;", "Lcom/mlk/taskmanager/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Welcome extends com.mlk.taskmanager.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.mlk.taskmanager.ui.navigation.Screen.Welcome INSTANCE = null;
        
        private Welcome() {
        }
    }
}