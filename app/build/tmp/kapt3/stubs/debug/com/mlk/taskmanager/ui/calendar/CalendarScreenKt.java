package com.mlk.taskmanager.ui.calendar;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a.\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u001a\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0003\u001a(\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0003\u00a8\u0006\u0017"}, d2 = {"CalendarScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/mlk/taskmanager/ui/calendar/CalendarViewModel;", "DateItem", "date", "Ljava/time/LocalDate;", "isSelected", "", "timeFilter", "Lcom/mlk/taskmanager/ui/calendar/TimeFilter;", "onClick", "Lkotlin/Function0;", "StatusChip", "status", "Lcom/mlk/taskmanager/ui/calendar/TaskStatus;", "TaskItem", "title", "", "category", "time", "app_debug"})
public final class CalendarScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void CalendarScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.calendar.CalendarViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DateItem(java.time.LocalDate date, boolean isSelected, com.mlk.taskmanager.ui.calendar.TimeFilter timeFilter, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TaskItem(java.lang.String title, java.lang.String category, java.lang.String time, com.mlk.taskmanager.ui.calendar.TaskStatus status) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatusChip(com.mlk.taskmanager.ui.calendar.TaskStatus status) {
    }
}