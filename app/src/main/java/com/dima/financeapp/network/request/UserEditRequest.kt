package com.dima.financeapp.network.request

data class UserEditRequest(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val datebirth: String,
    val gender: String
)