package com.mlk.taskmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mlk.taskmanager.data.converter.DayOfWeekConverter
import java.time.DayOfWeek
import java.time.LocalTime

@Entity(tableName = "routines")
@TypeConverters(DayOfWeekConverter::class)
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val time: LocalTime,
    val repeatDays: List<DayOfWeek>,
    val isEnabled: Boolean = true,
    val category: String? = null,
    val isLocationBased: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationRadius: Float? = null,
    val calendarEventId: String? = null, // ID de l'événement dans Google Calendar
    val isSyncedWithCalendar: Boolean = false // Indicateur si la routine est synchronisée
) 