package com.mlk.taskmanager.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mlk.taskmanager.data.dao.UserDao
import com.mlk.taskmanager.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")
private val CURRENT_USER_ID = longPreferencesKey("current_user_id")

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    @ApplicationContext private val context: Context
) : UserRepository {

    override suspend fun registerUser(email: String, password: String): Result<Long> {
        return try {
            // Check if user already exists
            val existingUser = userDao.getUserByEmail(email)
            if (existingUser != null) {
                Result.failure(Exception("Un utilisateur avec cet email existe déjà"))
            } else {
                val userId = userDao.insertUser(User(email = email, password = password))
                Result.success(userId)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginUser(email: String, password: String): Result<User> {
        return try {
            val user = userDao.login(email, password)
            if (user != null) {
                // Save user ID to DataStore
                context.dataStore.edit { preferences ->
                    preferences[CURRENT_USER_ID] = user.id
                }
                Result.success(user)
            } else {
                Result.failure(Exception("Email ou mot de passe incorrect"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserById(userId: Long): Flow<User?> {
        return userDao.getUserById(userId)
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return getCurrentUserId() != null
    }

    override suspend fun getCurrentUserId(): Long? {
        val preferences = context.dataStore.data.first()
        val userId = preferences[CURRENT_USER_ID]
        return userId
    }

    override suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences.remove(CURRENT_USER_ID)
        }
    }

    override suspend fun updateUserProfile(name: String?, profilePictureUrl: String?) {
        val userId = getCurrentUserId() ?: return
        val currentUser = userDao.getUserById(userId).first() ?: return
        
        val updatedUser = currentUser.copy(
            name = name ?: currentUser.name,
            profilePictureUrl = profilePictureUrl ?: currentUser.profilePictureUrl
        )
        
        userDao.insertUser(updatedUser)
    }
}
