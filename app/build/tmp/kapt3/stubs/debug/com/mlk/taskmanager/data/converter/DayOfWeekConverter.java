package com.mlk.taskmanager.data.converter;

/**
 * Convertisseur Room pour les listes de DayOfWeek
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0007J\u001a\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/mlk/taskmanager/data/converter/DayOfWeekConverter;", "", "()V", "gson", "Lcom/google/gson/Gson;", "fromDayOfWeekList", "", "value", "", "Ljava/time/DayOfWeek;", "toDayOfWeekList", "app_debug"})
public final class DayOfWeekConverter {
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    public DayOfWeekConverter() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String fromDayOfWeekList(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends java.time.DayOfWeek> value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.time.DayOfWeek> toDayOfWeekList(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
}