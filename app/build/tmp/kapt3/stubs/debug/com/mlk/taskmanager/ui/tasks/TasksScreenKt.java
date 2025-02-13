package com.mlk.taskmanager.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u001e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a6\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001aH\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u00122\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u001a\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007\u00a8\u0006\u0019"}, d2 = {"EmptyTasksMessage", "", "modifier", "Landroidx/compose/ui/Modifier;", "ErrorSnackbar", "message", "", "onDismiss", "Lkotlin/Function0;", "TaskItem", "task", "Lcom/mlk/taskmanager/data/model/Task;", "onClick", "onCheckedChange", "TasksList", "tasks", "", "onTaskClick", "Lkotlin/Function1;", "onTaskCheckedChange", "TasksScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/mlk/taskmanager/ui/tasks/TasksViewModel;", "app_debug"})
public final class TasksScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void TasksScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.tasks.TasksViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TasksList(java.util.List<com.mlk.taskmanager.data.model.Task> tasks, kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.data.model.Task, kotlin.Unit> onTaskClick, kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.data.model.Task, kotlin.Unit> onTaskCheckedChange, androidx.compose.ui.Modifier modifier) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void TaskItem(com.mlk.taskmanager.data.model.Task task, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, kotlin.jvm.functions.Function0<kotlin.Unit> onCheckedChange, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyTasksMessage(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ErrorSnackbar(java.lang.String message, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}