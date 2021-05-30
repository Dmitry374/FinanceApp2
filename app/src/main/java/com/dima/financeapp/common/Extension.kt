package com.dima.financeapp.common

import java.text.SimpleDateFormat
import java.util.Locale

fun Long.getDateText(): String {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale("ru"))
    return format.format(this)
}