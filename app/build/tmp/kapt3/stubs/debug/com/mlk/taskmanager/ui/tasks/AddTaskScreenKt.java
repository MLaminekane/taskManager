package com.mlk.taskmanager.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a \u0010\n\u001a\u00020\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\u0016\u0010\u000f\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a4\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a$\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\f2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u001bH\u0007\u001a*\u0010\u001c\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001bH\u0007\u001aj\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\f2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u001b2\u0006\u0010&\u001a\u00020#2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001b2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\u001bH\u0007\u001a&\u0010)\u001a\u00020\u00012\b\u0010*\u001a\u0004\u0018\u00010+2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00010\u001bH\u0007\u001a$\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00010\u001bH\u0007\u001a \u00101\u001a\u00020\u00012\b\u00102\u001a\u0004\u0018\u0001032\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a$\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\f2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u001bH\u0007\u001a4\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u00020\f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u001b2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0007\u001a\u0012\u0010>\u001a\u00020\f*\u00020?2\u0006\u0010@\u001a\u00020<\u00a8\u0006A"}, d2 = {"AddTaskScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/mlk/taskmanager/ui/tasks/TasksViewModel;", "settingsViewModel", "Lcom/mlk/taskmanager/ui/settings/SettingsViewModel;", "homeViewModel", "Lcom/mlk/taskmanager/ui/home/HomeViewModel;", "CategorySelector", "selectedCategory", "", "onCategoryClick", "Lkotlin/Function0;", "CreateTaskButton", "onClick", "DateTimeSelector", "selectedDate", "Ljava/time/LocalDate;", "selectedTime", "Ljava/time/LocalTime;", "onDateClick", "onTimeClick", "LocationSearchField", "query", "onQueryChange", "Lkotlin/Function1;", "LocationSearchResults", "results", "", "Lcom/mlk/taskmanager/util/PlacesUtil$PlaceSearchResult;", "onResultClick", "LocationSearchSection", "useLocation", "", "searchQuery", "onSearchQueryChange", "isSearching", "searchResults", "onToggleLocation", "MapView", "selectedLocation", "Lcom/google/android/gms/maps/model/LatLng;", "onMapClick", "PrioritySelector", "selectedPriority", "Lcom/mlk/taskmanager/data/model/Priority;", "onPrioritySelected", "ProjectSelector", "selectedProject", "Lcom/mlk/taskmanager/data/model/Project;", "onProjectClick", "TaskDescriptionField", "description", "onDescriptionChange", "TaskTitleField", "title", "onTitleChange", "progress", "", "total", "format", "", "digits", "app_debug"})
public final class AddTaskScreenKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String format(double $this$format, int digits) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TaskTitleField(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTitleChange, int progress, int total) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TaskDescriptionField(@org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDescriptionChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DateTimeSelector(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate selectedDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalTime selectedTime, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDateClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onTimeClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PrioritySelector(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Priority selectedPriority, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.data.model.Priority, kotlin.Unit> onPrioritySelected) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CategorySelector(@org.jetbrains.annotations.Nullable()
    java.lang.String selectedCategory, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCategoryClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProjectSelector(@org.jetbrains.annotations.Nullable()
    com.mlk.taskmanager.data.model.Project selectedProject, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProjectClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CreateTaskButton(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LocationSearchField(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQueryChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LocationSearchResults(@org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.util.PlacesUtil.PlaceSearchResult> results, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.util.PlacesUtil.PlaceSearchResult, kotlin.Unit> onResultClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LocationSearchSection(boolean useLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSearchQueryChange, boolean isSearching, @org.jetbrains.annotations.NotNull()
    java.util.List<com.mlk.taskmanager.util.PlacesUtil.PlaceSearchResult> searchResults, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.mlk.taskmanager.util.PlacesUtil.PlaceSearchResult, kotlin.Unit> onResultClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggleLocation) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MapView(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.maps.model.LatLng selectedLocation, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.google.android.gms.maps.model.LatLng, kotlin.Unit> onMapClick) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AddTaskScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.tasks.TasksViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.settings.SettingsViewModel settingsViewModel, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.home.HomeViewModel homeViewModel) {
    }
}