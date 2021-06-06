package com.dima.financeapp.model.net

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    @SerializedName("photo_url")
    val photoUrl: String,
    val password: String,
    val datebirth: String,
    val gender: String,
    val bills: List<BillResponse>
)