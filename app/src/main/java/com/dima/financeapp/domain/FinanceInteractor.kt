package com.dima.financeapp.domain

import com.dima.financeapp.model.domain.User
import com.dima.financeapp.model.net.BillResponse
import com.dima.financeapp.network.request.AddBillRequestItem
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

    fun addBill(name: String, amount: Double): Single<BillResponse> {
        return financeRepository.addBill(
            AddBillRequestItem(
                name = name,
                amount = amount,
                email = sharedPreferencesHelper.getUserEmail()
            )
        )
            .subscribeOn(Schedulers.io())
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