package com.demate.apppos.domain.model

data class Company(
    val id: String = "",
    val name: String = "",
    val cnpj: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val phone: String = "",
    val email: String = "",
    val userId: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
