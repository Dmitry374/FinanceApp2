package com.dima.financeapp.network.request

data class AddRecordRequestItem(
    val name: String,
    val sum: Double,
    val type: String,
    val color: Int = 0,
    val icon: Int,
    val date: Long,
    val billId: Int
)
