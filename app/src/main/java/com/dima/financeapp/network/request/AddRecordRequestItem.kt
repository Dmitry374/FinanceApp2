package com.dima.financeapp.network.request

data class AddRecordRequestItem(
    val name: String,
    val sum: Double,
    val type: String,
    val billName: String,
    val icon: Int,
    val date: Long,
    val info: String,
    val billId: Int
)
