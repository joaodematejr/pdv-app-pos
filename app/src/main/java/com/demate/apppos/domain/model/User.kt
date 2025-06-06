package com.demate.apppos.domain.model

data class User(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val companyId: String? = null,

    val ipAddress: String = "",
    val deviceId: String = "",
    val deviceName: String = "",
    val deviceOs: String = "",
    val deviceOsVersion: String = "",
    val deviceModel: String = "",
    val connectionType: String = "",

    val isOnline: Boolean = false,
    val isAdmin: Boolean = false,
    val isStaff: Boolean = false,

    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true,
    val isDeleted: Boolean = false

)