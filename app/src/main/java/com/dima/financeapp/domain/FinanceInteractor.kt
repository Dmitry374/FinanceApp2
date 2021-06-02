package com.dima.financeapp.domain

import com.dima.financeapp.common.Constants
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.model.domain.User
import com.dima.financeapp.network.request.AddBillRequestItem
import com.dima.financeapp.network.request.AddRecordRequestItem
import com.dima.financeapp.network.request.AuthorisationRequestItem
import com.dima.financeapp.repository.FinanceRepository
import com.dima.financeapp.sharedpreference.SharedPreferenceHelper
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FinanceInteractor(
    private val financeRepository: FinanceRepository,
    private val sharedPreferencesHelper: SharedPreferenceHelper
) {

    /**
     * Web
     */

    fun loginUser(authorisationRequestItem: AuthorisationRequestItem): Single<Unit> {
        return financeRepository.loginUser(authorisationRequestItem)
            .subscribeOn(Schedulers.io())
            .map { userResponse ->
                sharedPreferencesHelper.setSignInAccount(true)
                sharedPreferencesHelper.setUserEmail(userResponse.email)

                financeRepository.insertUser(userResponse)

                financeRepository.insertBills(userResponse.bills)

                userResponse.bills.forEach { bill ->
                    bill.records.forEach { record ->
                        financeRepository.insertRecord(bill, record)
                    }
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun registerUser(authorisationRequestItem: AuthorisationRequestItem): Single<Unit> {
        return financeRepository.registerUser(authorisationRequestItem)
            .subscribeOn(Schedulers.io())
            .map { userResponse ->
                sharedPreferencesHelper.setSignInAccount(true)
                sharedPreferencesHelper.setUserEmail(userResponse.email)

                financeRepository.insertUser(userResponse)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addBill(name: String, amount: Double): Single<BillItemUiModel.BillUiModel> {
        return financeRepository.addBill(
            AddBillRequestItem(
                name = name,
                amount = amount,
                email = sharedPreferencesHelper.getUserEmail()
            )
        )
            .subscribeOn(Schedulers.io())
            .map { billResponse ->
                financeRepository.insertBill(billResponse)
                financeRepository.getBillFromBillResponse(billResponse)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addRecord(addRecordRequestItem: AddRecordRequestItem, bill: Bill): Single<Record> {
        return financeRepository.addRecord(addRecordRequestItem)
            .subscribeOn(Schedulers.io())
            .map { recordResponse ->
                financeRepository.insertRecord(
                    billId = addRecordRequestItem.billId,
                    recordResponse = recordResponse
                )
                when (recordResponse.type) {
                    Constants.RECORD_TYPE_INCOME -> {
                        bill.amount = bill.amount + recordResponse.sum
                    }
                    Constants.RECORD_TYPE_CONSUMPTION -> {
                        bill.amount = bill.amount - recordResponse.sum
                    }
                }
                financeRepository.getRecordFromBillResponse(recordResponse)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * DB
     */

    fun getUser(): Single<User> {
        return financeRepository.getUser(sharedPreferencesHelper.getUserEmail())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getBills(): Single<List<BillItemUiModel.BillUiModel>> {
        return financeRepository.getBills()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}