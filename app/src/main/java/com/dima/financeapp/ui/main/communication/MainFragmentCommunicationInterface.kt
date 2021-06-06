package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Category
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.network.request.UserEditRequest
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel

interface MainFragmentCommunicationInterface {
    fun displayAddNewBillFragment()
    fun displayNewBill(bill: BillItemUiModel.BillUiModel)

    fun displayBillRecords(bill: Bill)

    fun onAddRecordScreen(bill: Bill)

    fun onCategoriesScreen()
    fun onSelectCategory(category: Category)

    fun displayNewRecord(record: Record)

    fun editUser(userEditRequest: UserEditRequest)

    fun logOut()
}