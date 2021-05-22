package com.dima.financeapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val datebirth: String,
    val gender: String
)