package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel

interface MainTabCommunication {
    fun displayBills(bills: List<BillItemUiModel.BillUiModel>)
}