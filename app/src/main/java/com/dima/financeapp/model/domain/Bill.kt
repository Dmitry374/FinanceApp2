package com.dima.financeapp.model.domain

data class Bill(
    val id: Int,
    val name: String,
    val amount: Double,
    val color: Int,
    val records: List<Record>
)