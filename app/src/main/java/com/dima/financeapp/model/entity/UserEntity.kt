package com.dima.financeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String,
    val password: String,
    val datebirth: String,
    val gender: String
)