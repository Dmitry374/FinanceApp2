package com.dima.financeapp.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Record(
    val id: Int,
    val name: String,
    val sum: Double,
    val type: String,
    val billName: String,
    val icon: Int,
    val date: Long
) : Parcelable