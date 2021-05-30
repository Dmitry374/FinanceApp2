package com.dima.financeapp.ui.main.main

import com.dima.financeapp.model.domain.Bill

sealed class BillItemUiModel {
    data class BillUiModel(val bill: Bill): BillItemUiModel()
    object AddNewBillUiModel : BillItemUiModel()
}