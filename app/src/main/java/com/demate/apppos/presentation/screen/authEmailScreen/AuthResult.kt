package com.demate.apppos.presentation.screen.authEmailScreen

import com.google.firebase.auth.FirebaseUser

sealed class AuthResult {
    object Initial : AuthResult()
    object Loading : AuthResult()
    data class Success(val user: FirebaseUser? = null) : AuthResult()
    data class Error(val message: String) : AuthResult()
}