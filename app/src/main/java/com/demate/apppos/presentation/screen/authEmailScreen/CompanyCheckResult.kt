package com.demate.apppos.presentation.screen.authEmailScreen

sealed class CompanyCheckResult {
    object Initial : CompanyCheckResult()
    object Loading : CompanyCheckResult()
    data class HasCompany(val hasCompany: Boolean) : CompanyCheckResult()
    data class Error(val message: String) : CompanyCheckResult()
}