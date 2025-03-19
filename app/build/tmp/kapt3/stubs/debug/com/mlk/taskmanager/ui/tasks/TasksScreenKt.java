package com.mlk.taskmanager.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001a(\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001aj\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001a<\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00062\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a6\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u001a\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"H\u0007\u00a8\u0006#"}, d2 = {"EmptyTasksMessage", "", "modifier", "Landroidx/compose/ui/Modifier;", "ErrorSnackbar", "message", "", "onDismiss", "Lkotlin/Function0;", "FilterDialog", "selectedPriorities", "", "Lcom/mlk/taskmanager/data/model/Priority;", "showCompletedTasks", "", "sortOption", "Lcom/mlk/taskmanager/ui/tasks/SortOption;", "onTogglePriority", "Lkotlin/Function1;", "onToggleShowCompleted", "onSelectSortOption", "SearchBar", "query", "onQueryChange", "onCloseSearch", "TaskItem", "task", "Lcom/mlk/taskmanager/data/model/Task;", "onClick", "onCheckedChange", "TasksScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/mlk/taskmanager/ui/tasks/TasksViewModel;", "app_debug"})
public final class TasksScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void TasksScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.tasks.TasksViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void TaskItem(com.mlk.taskmanager.data.model.Task task, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, kotlin.jvm.functions.Function0<kotlin.Unit> onCheckedChange, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyTasksMessage(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ErrorSnackbar(java.lang.String message, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SearchBar(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQueryChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCloseSearch, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void FilterDialog(@org.jetbrains.annotations.NotNull()
    java.util.Set<? extends com.mlk.taskmanager.data.model.Priority> selectedPriorities, boolean showCompletedTasks, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.tasks.SortOption sortOption, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.data.model.Priority, kotlin.Unit> onTogglePriority, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onToggleShowCompleted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.ui.tasks.SortOption, kotlin.Unit> onSelectSortOption, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}