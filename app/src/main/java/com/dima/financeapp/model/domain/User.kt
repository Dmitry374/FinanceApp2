package com.dima.financeapp.model.domain

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val datebirth: String,
    val gender: String
)