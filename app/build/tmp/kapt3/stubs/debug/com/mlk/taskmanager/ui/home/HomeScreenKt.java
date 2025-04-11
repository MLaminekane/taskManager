package com.mlk.taskmanager.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u00a6\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a`\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a\u001e\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001ac\u0010\u0019\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182K\u0010\u001b\u001aG\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00010\u001cH\u0003\u001a\b\u0010 \u001a\u00020\u0001H\u0003\u001a \u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0007\u001a\u001a\u0010(\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020'H\u0007\u001a\u001e\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001a\u001e\u0010,\u001a\u00020\u00012\u0006\u0010-\u001a\u00020.2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001a\u001e\u0010/\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0003\u001a$\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00032\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000103H\u0003\u001a8\u00104\u001a\u00020\u00012\f\u00105\u001a\b\u0012\u0004\u0012\u00020+062\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000103H\u0007\u001a\u0010\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020;H\u0007\u001a\u0010\u0010<\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%H\u0007\u001a\u001e\u0010=\u001a\u00020\u00012\u0006\u0010-\u001a\u00020.2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0003\u001aF\u0010>\u001a\u00020\u00012\f\u0010?\u001a\b\u0012\u0004\u0012\u00020.062\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0001032\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001a\"\u0010C\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0003\u001a \u0010D\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0003\u001a\u0010\u0010E\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#H\u0007\u001a8\u0010F\u001a\u00020\u00012\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u0016062\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0001032\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001a(\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020L2\b\u0010M\u001a\u0004\u0018\u00010N2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001a\u0018\u0010O\u001a\u00020\u00012\u0006\u0010P\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020\u0003H\u0003\u001a\u0010\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u0003H\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006U"}, d2 = {"AnimatedSummaryCard", "", "title", "", "count", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "animatedProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "animationOffsetX", "modifier", "Landroidx/compose/ui/Modifier;", "AnimatedSummaryCard-jA1GFJw", "(Ljava/lang/String;ILandroidx/compose/ui/graphics/vector/ImageVector;JJLandroidx/compose/animation/core/Animatable;FLandroidx/compose/ui/Modifier;)V", "AnimatedTaskCard", "task", "Lcom/mlk/taskmanager/data/model/Task;", "onClick", "Lkotlin/Function0;", "CreateProjectDialog", "onDismiss", "onConfirm", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "description", "EmptyTasksMessage", "HeaderSection", "uiState", "Lcom/mlk/taskmanager/ui/home/HomeUiState;", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/mlk/taskmanager/ui/home/HomeViewModel;", "HomeScreen", "ImprovedProjectCard", "project", "Lcom/mlk/taskmanager/data/model/Project;", "ImprovedRoutineCard", "routine", "Lcom/mlk/taskmanager/data/model/Routine;", "ProjectCard", "ProjectTypeSelector", "selectedType", "onTypeSelected", "Lkotlin/Function1;", "ProjectsSection", "projects", "", "onAddProject", "onProjectClick", "QuickActionItem", "item", "Lcom/mlk/taskmanager/ui/home/ActionItem;", "QuickActionsRow", "RoutineCardCompact", "RoutinesSection", "routines", "onAddRoutine", "onRoutineClick", "onSeeAllClick", "SummaryCard", "TaskCard", "TaskSummarySection", "UpcomingTasksSection", "tasks", "onTaskClick", "onAddTaskClick", "WeatherCard", "isLoading", "", "weatherData", "Lcom/mlk/taskmanager/data/model/WeatherResponse;", "WeatherInfoItem", "label", "value", "getWeatherIcon", "Landroidx/compose/ui/graphics/painter/Painter;", "condition", "app_debug"})
public final class HomeScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.HomeViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HeaderSection(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.HomeUiState uiState, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.HomeViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WeatherCard(boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.mlk.taskmanager.data.model.WeatherResponse weatherData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickActionsRow(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickActionItem(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.ActionItem item) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SummaryCard(java.lang.String title, int count, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProjectCard(com.mlk.taskmanager.data.model.Project project, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TaskCard(com.mlk.taskmanager.data.model.Task task, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyTasksMessage() {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void CreateProjectDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProjectTypeSelector(java.lang.String selectedType, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTypeSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RoutineCardCompact(com.mlk.taskmanager.data.model.Routine routine, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WeatherInfoItem(java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TaskSummarySection(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.HomeUiState uiState) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProjectsSection(@org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Project> projects, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddProject, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.data.model.Project, kotlin.Unit> onProjectClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ImprovedProjectCard(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Project project, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RoutinesSection(@org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Routine> routines, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddRoutine, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.data.model.Routine, kotlin.Unit> onRoutineClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSeeAllClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ImprovedRoutineCard(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void UpcomingTasksSection(@org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.data.model.Task> tasks, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.data.model.Task, kotlin.Unit> onTaskClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddTaskClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AnimatedTaskCard(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    /**
     * Retourne l'icône météo correspondant à la condition météo
     */
    @androidx.compose.runtime.Composable()
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.painter.Painter getWeatherIcon(@org.jetbrains.annotations.NotNull()
    java.lang.String condition) {
        return null;
    }
}