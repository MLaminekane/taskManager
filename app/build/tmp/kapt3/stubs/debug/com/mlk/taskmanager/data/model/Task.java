package com.mlk.taskmanager.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b3\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00a3\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\n\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\n\u00a2\u0006\u0002\u0010\u0018J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\nH\u00c6\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u000b\u00104\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u000b\u00106\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u00107\u001a\u00020\nH\u00c6\u0003J\t\u00108\u001a\u00020\u0005H\u00c6\u0003J\t\u00109\u001a\u00020\u0005H\u00c6\u0003J\t\u0010:\u001a\u00020\bH\u00c6\u0003J\t\u0010;\u001a\u00020\nH\u00c6\u0003J\t\u0010<\u001a\u00020\fH\u00c6\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u0010\u0010>\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u0010\u0010?\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003\u00a2\u0006\u0002\u0010)J\u00b2\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\n2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0017\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0002\u0010AJ\u0013\u0010B\u001a\u00020\n2\b\u0010C\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010D\u001a\u00020EH\u00d6\u0001J\t\u0010F\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010$R\u0011\u0010\u0017\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010$R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b%\u0010&R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b+\u0010&R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b.\u0010\u001dR\u0011\u0010\u0012\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010$R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001a\u00a8\u0006G"}, d2 = {"Lcom/mlk/taskmanager/data/model/Task;", "", "id", "", "title", "", "description", "dueDateTime", "Ljava/time/LocalDateTime;", "isCompleted", "", "priority", "Lcom/mlk/taskmanager/data/model/Priority;", "latitude", "", "longitude", "locationRadius", "", "reminderEnabled", "categoryId", "category", "projectId", "calendarEventId", "isSyncedWithCalendar", "(JLjava/lang/String;Ljava/lang/String;Ljava/time/LocalDateTime;ZLcom/mlk/taskmanager/data/model/Priority;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;ZLjava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Z)V", "getCalendarEventId", "()Ljava/lang/String;", "getCategory", "getCategoryId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDescription", "getDueDateTime", "()Ljava/time/LocalDateTime;", "getId", "()J", "()Z", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLocationRadius", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getLongitude", "getPriority", "()Lcom/mlk/taskmanager/data/model/Priority;", "getProjectId", "getReminderEnabled", "getTitle", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/time/LocalDateTime;ZLcom/mlk/taskmanager/data/model/Priority;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;ZLjava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Z)Lcom/mlk/taskmanager/data/model/Task;", "equals", "other", "hashCode", "", "toString", "app_debug"})
@androidx.room.Entity(tableName = "tasks")
public final class Task {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime dueDateTime = null;
    private final boolean isCompleted = false;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.model.Priority priority = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double latitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double longitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float locationRadius = null;
    private final boolean reminderEnabled = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long categoryId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String category = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long projectId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String calendarEventId = null;
    private final boolean isSyncedWithCalendar = false;
    
    public Task(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime dueDateTime, boolean isCompleted, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Priority priority, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Float locationRadius, boolean reminderEnabled, @org.jetbrains.annotations.Nullable()
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.Long projectId, @org.jetbrains.annotations.Nullable()
    java.lang.String calendarEventId, boolean isSyncedWithCalendar) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime getDueDateTime() {
        return null;
    }
    
    public final boolean isCompleted() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.model.Priority getPriority() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLatitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLongitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getLocationRadius() {
        return null;
    }
    
    public final boolean getReminderEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getCategoryId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getProjectId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCalendarEventId() {
        return null;
    }
    
    public final boolean isSyncedWithCalendar() {
        return false;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    public final boolean component15() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.model.Priority component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mlk.taskmanager.data.model.Task copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime dueDateTime, boolean isCompleted, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.model.Priority priority, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Float locationRadius, boolean reminderEnabled, @org.jetbrains.annotations.Nullable()
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.Long projectId, @org.jetbrains.annotations.Nullable()
    java.lang.String calendarEventId, boolean isSyncedWithCalendar) {
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