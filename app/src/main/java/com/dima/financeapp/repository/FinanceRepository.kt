package com.dima.financeapp.repository

import com.dima.financeapp.common.DataMapper
import com.dima.financeapp.database.FinanceDao
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.User
import com.dima.financeapp.model.net.BillResponse
import com.dima.financeapp.model.net.RecordResponse
import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.network.ApiService
import com.dima.financeapp.network.request.AddBillRequestItem
import com.dima.financeapp.network.request.AuthorisationRequestItem
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import io.reactivex.Single

class FinanceRepository(
    private val apiService: ApiService,
    private val financeDao: FinanceDao,
    private val dataMapper: DataMapper
) {

    /**
     * Web
     */

    fun loginUser(authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse> {
        return apiService.loginUser(authorisationRequestItem)
    }

    fun registerUser(authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse> {
        return apiService.registerUser(authorisationRequestItem)
    }

    fun addBill(addBillRequestItem: AddBillRequestItem): Single<BillResponse> {
        return apiService.addBill(addBillRequestItem)
    }

    /**
     * DB
     */

    fun insertUser(userResponse: UserResponse) {
        financeDao.insertUser(dataMapper.userResponseToUserEntity(userResponse))
    }

    fun getUser(email: String): Single<User> {
        return financeDao.getUser(email)
            .map { userEntity ->
                dataMapper.userUserEntityToUserDomain(userEntity)
            }
    }

    fun updateUser(userResponse: UserResponse) {
        financeDao.updateUser(dataMapper.userResponseToUserEntity(userResponse))
    }

    fun clearUser() {
        financeDao.clearUserData()
    }

    fun insertBill(billResponse: BillResponse) {
        financeDao.insertBill(dataMapper.billResponseToBillEntity(billResponse))
    }

    fun insertBills(bills: List<BillResponse>) {
        financeDao.insertBills(dataMapper.listBillResponseToListBillEntity(bills))
    }

    fun insertRecord(billResponse: BillResponse, recordResponse: RecordResponse) {
        financeDao.insertRecord(dataMapper.recordResponseToRecordEntity(recordResponse, billResponse.id))
    }

    fun getBills(): Single<List<BillItemUiModel.BillUiModel>> {
        return financeDao.getBills()
            .map { billsWithRecordsRequest ->
                val bills = dataMapper.billsWithRecordsListToBillsList(billsWithRecordsRequest)
                dataMapper.billsWithRecordsListToBillsUiModelsList(bills)
            }
    }

    /**
     * Convert
     */

    fun getBillFromBillResponse(billResponse: BillResponse): Bill {
        return dataMapper.billResponseToBill(billResponse)
    }
}