package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Category
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel

interface MainFragmentCommunicationInterface {
    fun displayAddNewBillFragment()
    fun displayNewBill(bill: BillItemUiModel.BillUiModel)

    fun displayBillRecords(bill: Bill)

    fun onAddRecordScreen(bill: Bill)

    fun onCategoriesScreen()
    fun onSelectCategory(category: Category)
}