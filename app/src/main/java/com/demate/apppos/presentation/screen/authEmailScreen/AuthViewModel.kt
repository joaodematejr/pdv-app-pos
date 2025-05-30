package com.demate.apppos.presentation.screen.authEmailScreen

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// AuthViewModel.kt
class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun signInWithEmail(email: String, password: String) {
        _uiState.value = AuthUiState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _uiState.value = AuthUiState.Success
            }
            .addOnFailureListener { e ->
                val errorMessage = when (e) {
                    is FirebaseAuthInvalidCredentialsException -> "Email ou senha inválidos"
                    is FirebaseAuthInvalidUserException -> "Usuário não encontrado"
                    else -> "Erro ao fazer login: ${e.message}"
                }
                _uiState.value = AuthUiState.Error(errorMessage)
            }
    }

    fun createAccountWithEmail(email: String, password: String) {
        _uiState.value = AuthUiState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _uiState.value = AuthUiState.Success
            }
            .addOnFailureListener { e ->
                val errorMessage = when (e) {
                    is FirebaseAuthWeakPasswordException -> "Senha muito fraca"
                    is FirebaseAuthInvalidCredentialsException -> "Email inválido"
                    is FirebaseAuthUserCollisionException -> "Email já cadastrado"
                    else -> "Erro ao criar conta: ${e.message}"
                }
                _uiState.value = AuthUiState.Error(errorMessage)
            }
    }
}