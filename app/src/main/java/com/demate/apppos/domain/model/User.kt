package com.demate.apppos.domain.model

data class User(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val companyId: String? = null
)