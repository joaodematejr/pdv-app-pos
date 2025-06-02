package com.demate.apppos.data.repository

import com.demate.apppos.domain.repository.AuthRepository
import com.demate.apppos.presentation.screen.authEmailScreen.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override fun signInWithEmail(email: String, password: String): Flow<AuthResult> = callbackFlow {
        trySend(AuthResult.Loading)

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(AuthResult.Success)
            }
            .addOnFailureListener { e ->
                val errorMessage = when (e) {
                    is FirebaseAuthInvalidCredentialsException -> "Email ou senha inválidos"
                    is FirebaseAuthInvalidUserException -> "Usuário não encontrado"
                    else -> "Erro ao fazer login: ${e.message}"
                }
                trySend(AuthResult.Error(errorMessage))
            }

        awaitClose()
    }

    override fun createAccountWithEmail(email: String, password: String): Flow<AuthResult> = callbackFlow {
        trySend(AuthResult.Loading)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(AuthResult.Success)
            }
            .addOnFailureListener { e ->
                val errorMessage = when (e) {
                    is FirebaseAuthWeakPasswordException -> "Senha muito fraca"
                    is FirebaseAuthInvalidCredentialsException -> "Email inválido"
                    is FirebaseAuthUserCollisionException -> "Email já cadastrado"
                    else -> "Erro ao criar conta: ${e.message}"
                }
                trySend(AuthResult.Error(errorMessage))
            }

        awaitClose()
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}