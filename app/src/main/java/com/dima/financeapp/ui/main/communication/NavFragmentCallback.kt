package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel

interface NavFragmentCallback {
    fun displayAddNewBillFragment()
    fun displayNewBill(bill: BillItemUiModel.BillUiModel)
}