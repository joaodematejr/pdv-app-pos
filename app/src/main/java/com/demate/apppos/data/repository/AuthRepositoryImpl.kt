package com.demate.apppos.data.repository

import android.app.Application
import com.demate.apppos.domain.model.User
import com.demate.apppos.domain.repository.AuthRepository
import com.demate.apppos.presentation.screen.authEmailScreen.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val application: Application,
) : AuthRepository {

    override fun signInWithEmail(email: String, password: String): Flow<AuthResult> = callbackFlow {
        trySend(AuthResult.Loading)

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(AuthResult.Success)
            }
            .addOnFailureListener { e ->
                val errorMessage = when (e) {
                    is FirebaseAuthInvalidCredentialsException -> application.getString(com.demate.apppos.R.string.invalid_email_or_password)
                    is FirebaseAuthInvalidUserException -> application.getString(com.demate.apppos.R.string.user_not_found)
                    else -> "${application.getString(com.demate.apppos.R.string.login_error)} ${e.message}"
                }
                trySend(AuthResult.Error(errorMessage))
            }

        awaitClose()
    }

    override fun createAccountWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user

            if (user != null) {
                val userData = User(
                    name = name,
                    email = email,
                    phone = phone
                )
                firestore.collection("users")
                    .document(user.uid)
                    .set(userData)
                    .await()
                emit(AuthResult.Success)
            } else {
                emit(AuthResult.Error(application.getString(com.demate.apppos.R.string.error_creating_user)))
            }
        } catch (e: FirebaseAuthException) {
            emit(AuthResult.Error(getFirebaseErrorMessage(e)))
        } catch (e: Exception) {
            emit(AuthResult.Error(e.localizedMessage ?: application.getString(com.demate.apppos.R.string.unknown_error)))
        }
    }.flowOn(Dispatchers.IO)

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun checkUserHasCompany(): Flow<Boolean> = callbackFlow {
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("companies")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .addOnSuccessListener { documents ->
                    trySend(!documents.isEmpty)
                }
                .addOnFailureListener {
                    trySend(false)
                }
        } else {
            trySend(false)
        }

        awaitClose()
    }

    private fun getFirebaseErrorMessage(e: FirebaseAuthException): String {
        return when (e.errorCode) {
            "ERROR_INVALID_EMAIL" -> application.getString(com.demate.apppos.R.string.invalid_email)
            "ERROR_WRONG_PASSWORD" -> application.getString(com.demate.apppos.R.string.incorrect_password)
            "ERROR_USER_NOT_FOUND" -> application.getString(com.demate.apppos.R.string.user_not_found_message)
            "ERROR_USER_DISABLED" -> application.getString(com.demate.apppos.R.string.user_disabled)
            "ERROR_EMAIL_ALREADY_IN_USE" -> application.getString(com.demate.apppos.R.string.email_already_in_use)
            "ERROR_WEAK_PASSWORD" -> application.getString(com.demate.apppos.R.string.weak_password)
            else -> e.localizedMessage ?: application.getString(com.demate.apppos.R.string.authentication_error)
        }
    }
}