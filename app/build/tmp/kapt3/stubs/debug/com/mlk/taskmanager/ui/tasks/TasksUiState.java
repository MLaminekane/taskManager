package com.mlk.taskmanager.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u0013J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003J\t\u0010(\u001a\u00020\u0007H\u00c6\u0003J\t\u0010)\u001a\u00020\u0011H\u00c6\u0003J\u0081\u0001\u0010*\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00072\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010+\u001a\u00020\u00072\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\tH\u00d6\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017\u00a8\u00060"}, d2 = {"Lcom/mlk/taskmanager/ui/tasks/TasksUiState;", "", "tasks", "", "Lcom/mlk/taskmanager/data/model/Task;", "filteredTasks", "isLoading", "", "error", "", "searchQuery", "isSearchActive", "selectedPriorities", "", "Lcom/mlk/taskmanager/data/model/Priority;", "showCompletedTasks", "sortOption", "Lcom/mlk/taskmanager/ui/tasks/SortOption;", "isFilterDialogVisible", "(Ljava/util/List;Ljava/util/List;ZLjava/lang/String;Ljava/lang/String;ZLjava/util/Set;ZLcom/mlk/taskmanager/ui/tasks/SortOption;Z)V", "getError", "()Ljava/lang/String;", "getFilteredTasks", "()Ljava/util/List;", "()Z", "getSearchQuery", "getSelectedPriorities", "()Ljava/util/Set;", "getShowCompletedTasks", "getSortOption", "()Lcom/mlk/taskmanager/ui/tasks/SortOption;", "getTasks", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class TasksUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.data.model.Task> tasks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.mlk.taskmanager.data.model.Task> filteredTasks = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String searchQuery = null;
    private final boolean isSearchActive = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<com.mlk.taskmanager.data.model.Priority> selectedPriorities = null;
    private final boolean showCompletedTasks = false;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.ui.tasks.SortOption sortOption = null;
    private final boolean isFilterDialogVisible = false;
    
    public TasksUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> tasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> filteredTasks, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, boolean isSearchActive, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends com.mlk.taskmanager.data.model.Priority> selectedPriorities, boolean showCompletedTasks, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.tasks.SortOption sortOption, boolean isFilterDialogVisible) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> getTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> getFilteredTasks() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchQuery() {
        return null;
    }
    
    public final boolean isSearchActive() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<com.mlk.taskmanager.data.model.Priority> getSelectedPriorities() {
        return null;
    }
    
    public final boolean getShowCompletedTasks() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.tasks.SortOption getSortOption() {
        return null;
    }
    
    public final boolean isFilterDialogVisible() {
        return false;
    }
    
    public TasksUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> component1() {
        return null;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.mlk.taskmanager.data.model.Task> component2() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<com.mlk.taskmanager.data.model.Priority> component7() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.tasks.SortOption component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.ui.tasks.TasksUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> tasks, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> filteredTasks, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, boolean isSearchActive, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends com.mlk.taskmanager.data.model.Priority> selectedPriorities, boolean showCompletedTasks, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.tasks.SortOption sortOption, boolean isFilterDialogVisible) {
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