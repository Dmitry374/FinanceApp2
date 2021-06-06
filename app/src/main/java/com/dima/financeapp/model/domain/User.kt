package com.dima.financeapp.model.domain

data class User(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val photoUrl: String,
    val password: String,
    val datebirth: String,
    val gender: String
)