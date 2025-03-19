package com.mlk.taskmanager.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    private val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let {
            return LocalDateTime.parse(it, dateTimeFormatter)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.format(dateTimeFormatter)
    }
    
    @TypeConverter
    fun fromTimeString(value: String?): LocalTime? {
        return value?.let {
            return LocalTime.parse(it, timeFormatter)
        }
    }
    
    @TypeConverter
    fun timeToString(time: LocalTime?): String? {
        return time?.format(timeFormatter)
    }
    
    @TypeConverter
    fun fromDayOfWeekSet(value: Set<DayOfWeek>?): String? {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toDayOfWeekSet(value: String?): Set<DayOfWeek>? {
        if (value == null) return emptySet()
        val listType = object : TypeToken<Set<DayOfWeek>>() {}.type
        return gson.fromJson(value, listType)
    }
} 