package com.dima.financeapp.model.net.currency

data class CurrencyRatesResponse(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Valute
)