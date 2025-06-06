package com.demate.apppos.presentation.screen.companyRegistrationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.apppos.data.session.SessionManager
import com.demate.apppos.domain.model.Company
import com.demate.apppos.domain.repository.CompanyRepository
import com.demate.apppos.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyRegistrationViewModel @Inject constructor(
    private val companyRepository: CompanyRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerCompany(
        name: String,
        cnpj: String,
        address: String,
        city: String,
        state: String,
        phone: String,
        email: String
    ) {
        val userId = sessionManager.getUserId() ?: return

        viewModelScope.launch {
            val company = Company(
                name = name,
                cnpj = cnpj,
                address = address,
                city = city,
                state = state,
                phone = phone,
                email = email,
                userId = userId
            )

            companyRepository.registerCompany(company).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _registrationState.value = RegistrationState.Loading
                    }

                    is Resource.Success -> {
                        _registrationState.value = RegistrationState.Success
                    }

                    is Resource.Error -> {
                        _registrationState.value =
                            RegistrationState.Error(result.message ?: "Erro desconhecido")
                    }
                }
            }
        }
    }
}