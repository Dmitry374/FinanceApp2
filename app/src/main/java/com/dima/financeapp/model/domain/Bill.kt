package com.dima.financeapp.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bill(
    val id: Int,
    val name: String,
    var amount: Double,
    val color: Int,
    var records: List<Record>
) : Parcelable