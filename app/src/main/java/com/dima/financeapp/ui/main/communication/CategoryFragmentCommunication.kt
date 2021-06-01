package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Category

interface CategoryFragmentCommunication {
    fun setBill(bill: Bill)
    fun selectCategory(category: Category)
}