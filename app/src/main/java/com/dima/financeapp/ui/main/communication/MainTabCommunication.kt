package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.domain.User
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel

interface MainTabCommunication {
    fun userData(user: User)
    fun displayBills(bills: List<BillItemUiModel.BillUiModel>)
}