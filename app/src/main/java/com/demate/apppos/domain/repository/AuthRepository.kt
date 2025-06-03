package com.demate.apppos.domain.repository

import com.demate.apppos.presentation.screen.authEmailScreen.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signInWithEmail(email: String, password: String): Flow<AuthResult>
    fun createAccountWithEmail(name: String, phone: String, email: String, password: String): Flow<AuthResult>
    fun isUserLoggedIn(): Boolean
    fun checkUserHasCompany(): Flow<Boolean>
}