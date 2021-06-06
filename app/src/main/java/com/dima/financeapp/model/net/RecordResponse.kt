package com.dima.financeapp.model.net

import com.google.gson.annotations.SerializedName

data class RecordResponse(
    val id: Int,
    val name: String,
    val sum: Double,
    val type: String,
    @SerializedName("bill_name")
    val billName: String,
    val icon: Int,
    val date: Long
)