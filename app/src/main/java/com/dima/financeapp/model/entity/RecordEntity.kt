package com.dima.financeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "record",
    foreignKeys = [ForeignKey(
        entity = BillEntity::class,
        parentColumns = ["id"],
        childColumns = ["bill_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RecordEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val sum: Double,
    val type: String,
    @ColumnInfo(name = "bill_name")
    val billName: String,
    val icon: Int,
    val date: Long,
    @ColumnInfo(name = "bill_id", index = true)
    val billId: Int
)