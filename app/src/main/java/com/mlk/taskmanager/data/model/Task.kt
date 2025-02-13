package com.mlk.taskmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val dueDateTime: LocalDateTime,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationRadius: Float? = null, // in meters
    val reminderEnabled: Boolean = true,
    val categoryId: Long? = null
)

enum class Priority {
    LOW, MEDIUM, HIGH
} 