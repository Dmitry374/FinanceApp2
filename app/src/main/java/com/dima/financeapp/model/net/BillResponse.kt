package com.dima.financeapp.model.net

data class BillResponse(
    val id: Int,
    val name: String,
    val amount: Double,
    val color: Int,
    val records: List<RecordResponse>
)