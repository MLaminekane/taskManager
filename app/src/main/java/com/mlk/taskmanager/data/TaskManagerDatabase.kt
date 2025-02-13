package com.mlk.taskmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mlk.taskmanager.data.dao.RoutineDao
import com.mlk.taskmanager.data.dao.TaskDao
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.model.Task
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Database(
    entities = [Task::class, Routine::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TaskManagerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun routineDao(): RoutineDao
}

class Converters {
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    private val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
    
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it, dateTimeFormatter) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.format(dateTimeFormatter)
    }

    @TypeConverter
    fun fromTimeString(value: String?): LocalTime? {
        return value?.let { LocalTime.parse(it, timeFormatter) }
    }

    @TypeConverter
    fun timeToString(time: LocalTime?): String? {
        return time?.format(timeFormatter)
    }

    @TypeConverter
    fun fromDayOfWeekSet(value: String?): Set<DayOfWeek>? {
        return value?.split(",")?.map { DayOfWeek.valueOf(it) }?.toSet()
    }

    @TypeConverter
    fun toDayOfWeekString(days: Set<DayOfWeek>?): String? {
        return days?.joinToString(",") { it.name }
    }
} 