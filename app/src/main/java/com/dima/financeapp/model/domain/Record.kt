package com.dima.financeapp.model.domain

data class Record(
    val id: Int,
    val name: String,
    val sum: Int,
    val type: String,
    val color: Int,
    val icon: Int,
    val date: Long
)