package com.demate.apppos.presentation.screen.authEmailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.apppos.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthResult>(AuthResult.Initial)
    val uiState = _uiState.asStateFlow()

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signInWithEmail(email, password).collect { result ->
                _uiState.value = result
            }
        }
    }

    fun createAccountWithEmail(email: String, password: String) {
        viewModelScope.launch {
            authRepository.createAccountWithEmail(email, password).collect { result ->
                _uiState.value = result
            }
        }
    }

}