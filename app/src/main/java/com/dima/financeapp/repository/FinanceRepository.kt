package com.dima.financeapp.repository

import com.dima.financeapp.common.DataMapper
import com.dima.financeapp.database.FinanceDao
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.model.domain.User
import com.dima.financeapp.model.net.BillResponse
import com.dima.financeapp.model.net.RecordResponse
import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.network.ApiService
import com.dima.financeapp.network.request.AddBillRequestItem
import com.dima.financeapp.network.request.AddRecordRequestItem
import com.dima.financeapp.network.request.AuthorisationRequestItem
import com.dima.financeapp.network.request.UserEditRequest
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

    fun loadUserData(email: String): Single<UserResponse> {
        return apiService.signInUser(email)
    }

    fun registerUser(authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse> {
        return apiService.registerUser(authorisationRequestItem)
    }

    fun editUser(userEditRequest: UserEditRequest): Single<UserResponse> {
        return apiService.editUser(userEditRequest.id, userEditRequest)
    }

    fun addBill(addBillRequestItem: AddBillRequestItem): Single<BillResponse> {
        return apiService.addBill(addBillRequestItem)
    }

    fun addRecord(addRecordRequestItem: AddRecordRequestItem): Single<RecordResponse> {
        return apiService.addRecord(addRecordRequestItem)
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

    fun insertRecord(billId: Int, recordResponse: RecordResponse) {
        financeDao.insertRecord(dataMapper.recordResponseToRecordEntity(recordResponse, billId))
    }

    fun getBills(): Single<List<BillItemUiModel.BillUiModel>> {
        return financeDao.getBills()
            .map { billsWithRecordsRequest ->
                val bills = dataMapper.billsWithRecordsListToBillsList(billsWithRecordsRequest)
                dataMapper.billsWithRecordsListToBillsUiModelsList(bills)
            }
    }

    fun getLastRecords(count: Int): Single<List<Record>> {
        return financeDao.getLastRecords(count)
            .map { recordEntityList ->
                dataMapper.recordEntitiesToRecords(recordEntityList)
            }
    }

    fun clearAllDataFromDB() {
        financeDao.clearUserData()
        financeDao.clearBillsData()
        financeDao.clearRecordsData()
    }

    /**
     * Convert
     */

    fun getBillFromBillResponse(billResponse: BillResponse): BillItemUiModel.BillUiModel {
        val bill = dataMapper.billResponseToBill(billResponse)
        return dataMapper.billToBillUiModel(bill)
    }

    fun getRecordFromBillResponse(recordResponse: RecordResponse): Record {
        return dataMapper.recordResponseToRecord(recordResponse)
    }
}