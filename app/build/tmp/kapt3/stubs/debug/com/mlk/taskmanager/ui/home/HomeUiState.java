package com.mlk.taskmanager.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\b\u0087\b\u0018\u00002\u00020\u0001Bc\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\r\u00a2\u0006\u0002\u0010\u0011J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u00c6\u0003J\t\u0010\"\u001a\u00020\u000bH\u00c6\u0003J\t\u0010#\u001a\u00020\rH\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003J\t\u0010%\u001a\u00020\rH\u00c6\u0003Jg\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\'\u001a\u00020\r2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001J\t\u0010*\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0017R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019\u00a8\u0006+"}, d2 = {"Lcom/mlk/taskmanager/ui/home/HomeUiState;", "", "assignedTasks", "", "completedTasks", "todayTasks", "", "Lcom/mlk/taskmanager/data/model/Task;", "projects", "Lcom/mlk/taskmanager/ui/home/Project;", "selectedFilter", "Lcom/mlk/taskmanager/ui/home/TaskFilter;", "isLoading", "", "error", "", "showCreateProjectDialog", "(IILjava/util/List;Ljava/util/List;Lcom/mlk/taskmanager/ui/home/TaskFilter;ZLjava/lang/String;Z)V", "getAssignedTasks", "()I", "getCompletedTasks", "getError", "()Ljava/lang/String;", "()Z", "getProjects", "()Ljava/util/List;", "getSelectedFilter", "()Lcom/mlk/taskmanager/ui/home/TaskFilter;", "getShowCreateProjectDialog", "getTodayTasks", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class HomeUiState {
    private final int assignedTasks = 0;
    private final int completedTasks = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.data.model.Task> todayTasks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.ui.home.Project> projects = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.ui.home.TaskFilter selectedFilter = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean showCreateProjectDialog = false;
    
    public HomeUiState(int assignedTasks, int completedTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> todayTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.ui.home.Project> projects, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.TaskFilter selectedFilter, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showCreateProjectDialog) {
        super();
    }
    
    public final int getAssignedTasks() {
        return 0;
    }
    
    public final int getCompletedTasks() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> getTodayTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.ui.home.Project> getProjects() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.home.TaskFilter getSelectedFilter() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final boolean getShowCreateProjectDialog() {
        return false;
    }
    
    public HomeUiState() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.ui.home.Project> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.home.TaskFilter component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.home.HomeUiState copy(int assignedTasks, int completedTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> todayTasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.ui.home.Project> projects, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.TaskFilter selectedFilter, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showCreateProjectDialog) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}