package com.dima.financeapp.network.request

data class AddBillRequestItem(val name: String, val amount: Double, val color: Int = 0, val email: String)