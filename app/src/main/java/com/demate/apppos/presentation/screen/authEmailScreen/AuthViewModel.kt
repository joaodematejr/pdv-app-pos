package com.demate.apppos.presentation.screen.authEmailScreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.apppos.R
import com.demate.apppos.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val application: Application
) : ViewModel() {


    private val _uiState = MutableStateFlow<AuthResult>(AuthResult.Initial)
    val uiState = _uiState.asStateFlow()

    private val _companyCheckState = MutableStateFlow<CompanyCheckResult>(CompanyCheckResult.Initial)
    val companyCheckState = _companyCheckState.asStateFlow()

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signInWithEmail(email, password).collect { result ->
                _uiState.value = result

                if (result is AuthResult.Success) {
                    checkUserHasCompany()
                }
            }
        }
    }

    fun createAccountWithEmail(name: String, phone: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.createAccountWithEmail(name, phone, email, password).collect { result ->
                _uiState.value = result

                if (result is AuthResult.Success) {
                    checkUserHasCompany()
                }
            }
        }
    }

    fun checkUserHasCompany() {
        _companyCheckState.value = CompanyCheckResult.Loading
        viewModelScope.launch {
            try {
                authRepository.checkUserHasCompany().collect { hasCompany ->
                    _companyCheckState.value = CompanyCheckResult.HasCompany(hasCompany)
                }
            } catch (e: Exception) {
                _companyCheckState.value = CompanyCheckResult.Error(e.message ?: application.getString(R.string.error_checking_company))
            }
        }
    }

}