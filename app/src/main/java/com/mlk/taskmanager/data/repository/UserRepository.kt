package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(email: String, password: String, name: String): Result<Long>
    suspend fun loginUser(email: String, password: String): Result<User>
    suspend fun getUserById(userId: Long): Flow<User?>
    suspend fun isUserLoggedIn(): Boolean
    suspend fun getCurrentUserId(): Long?
    suspend fun logout()
    suspend fun updateUserProfile(name: String?, profilePictureUrl: String?)
}
