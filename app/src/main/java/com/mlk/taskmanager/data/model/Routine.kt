package com.mlk.taskmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime

@Entity(tableName = "routines")
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val time: LocalTime,
    val repeatDays: Set<DayOfWeek>,
    val isEnabled: Boolean = true,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationRadius: Float? = null, // in meters
    val categoryId: Long? = null
) 