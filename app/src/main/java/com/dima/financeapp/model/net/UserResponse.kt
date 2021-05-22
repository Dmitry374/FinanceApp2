package com.dima.financeapp.model.net

data class UserResponse(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val datebirth: String,
    val gender: String
)