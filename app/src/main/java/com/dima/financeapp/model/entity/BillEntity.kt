package com.dima.financeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "bill",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
class BillEntity(
    val id: Int,
    val name: String,
    val amount: Double,
    val color: Int,
    @ColumnInfo(name = "user_id", index = true)
    val userId: Int
)