package com.dima.financeapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill")
data class BillEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val amount: Double,
    val color: Int,
)