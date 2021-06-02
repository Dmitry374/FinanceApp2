package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Record

interface RecordsFragmentCommunication {
    fun setBill(bill: Bill)
    fun displayNewRecord(record: Record)
}