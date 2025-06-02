package com.demate.apppos.presentation.screen.authEmailScreen

sealed class AuthResult {
    object Initial : AuthResult()
    object Loading : AuthResult()
    object Success : AuthResult()
    data class Error(val message: String) : AuthResult()
}