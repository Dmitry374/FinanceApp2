package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.domain.Bill

interface MainTabCommunication {
    fun displayBills(bills: List<Bill>)
}