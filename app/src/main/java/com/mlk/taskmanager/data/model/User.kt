package com.mlk.taskmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val password: String, // In a real app, this should be hashed
    val name: String? = null,
    val profilePictureUrl: String? = null
)
