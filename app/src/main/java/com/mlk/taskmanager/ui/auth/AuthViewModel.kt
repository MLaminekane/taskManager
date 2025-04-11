package com.mlk.taskmanager.ui.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlk.taskmanager.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false,
    val currentUserId: Long? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            val isLoggedIn = userRepository.isUserLoggedIn()
            val currentUserId = userRepository.getCurrentUserId()
            _uiState.value = _uiState.value.copy(
                isLoggedIn = isLoggedIn,
                currentUserId = currentUserId
            )
        }
    }

    fun updateEmail(value: String) {
        email = value
    }

    fun updatePassword(value: String) {
        password = value
    }

    fun updateConfirmPassword(value: String) {
        confirmPassword = value
    }

    fun login() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            if (email.isBlank() || password.isBlank()) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Veuillez remplir tous les champs"
                )
                return@launch
            }
            
            val result = userRepository.loginUser(email, password)
            
            result.fold(
                onSuccess = { user ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        currentUserId = user.id,
                        errorMessage = null
                    )
                    clearInputs()
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Une erreur est survenue"
                    )
                }
            )
        }
    }

    fun register() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Veuillez remplir tous les champs"
                )
                return@launch
            }
            
            if (password != confirmPassword) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Les mots de passe ne correspondent pas"
                )
                return@launch
            }
            
            val result = userRepository.registerUser(email, password)
            
            result.fold(
                onSuccess = { userId ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = null
                    )
                    clearInputs()
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Une erreur est survenue"
                    )
                }
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            _uiState.value = _uiState.value.copy(
                isLoggedIn = false,
                currentUserId = null
            )
        }
    }

    private fun clearInputs() {
        email = ""
        password = ""
        confirmPassword = ""
    }
}
