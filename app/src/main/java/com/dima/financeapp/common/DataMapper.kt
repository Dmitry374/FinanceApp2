package com.dima.financeapp.common

import com.dima.financeapp.database.query.BillsWithRecordsRequest
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.model.domain.User
import com.dima.financeapp.model.entity.BillEntity
import com.dima.financeapp.model.entity.RecordEntity
import com.dima.financeapp.model.entity.UserEntity
import com.dima.financeapp.model.net.BillResponse
import com.dima.financeapp.model.net.RecordResponse
import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel

class DataMapper {

    /**
     * User
     */
    fun userResponseToUserEntity(userResponse: UserResponse): UserEntity {
        return userResponse.toUserEntity()
    }

    private fun UserResponse.toUserEntity() = UserEntity(
        id = this.id,
        name = this.name,
        surname = this.surname,
        email = this.email,
        password = this.password,
        datebirth = this.datebirth,
        gender = this.gender
    )

    fun userUserEntityToUserDomain(userEntity: UserEntity): User {
        return userEntity.toUserDomain()
    }

    private fun UserEntity.toUserDomain() = User(
        id = this.id,
        name = this.name,
        surname = this.surname,
        email = this.email,
        password = this.password,
        datebirth = this.datebirth,
        gender = this.gender
    )

    /**
     * Bill
     */
    fun listBillResponseToListBillEntity(billResponseList: List<BillResponse>): List<BillEntity> {
        return billResponseList.toBillEntityList()
    }

    private fun List<BillResponse>.toBillEntityList() = this.map { it.toBillEntity() }

    fun billResponseToBillEntity(billResponse: BillResponse): BillEntity {
        return billResponse.toBillEntity()
    }

    private fun BillResponse.toBillEntity() = BillEntity(
        id = this.id,
        name = this.name,
        amount = this.amount,
        color = this.color,
    )

    fun billResponseToBill(billResponse: BillResponse): Bill {
        return billResponse.toBill()
    }

    private fun BillResponse.toBill() = Bill(
        id = this.id,
        name = this.name,
        amount = this.amount,
        color = this.color,
        records = this.records.toRecordsList()
    )

    private fun List<RecordResponse>.toRecordsList() = this.map { it.toRecord() }

    private fun RecordResponse.toRecord() = Record(
        id = this.id,
        name = this.name,
        sum = this.sum,
        type = this.type,
        color = this.color,
        icon = this.icon,
        date = this.date,
    )

    fun recordResponseToRecord(recordResponse: RecordResponse): Record {
        return recordResponse.toRecord()
    }

    /**
     * Record
     */
    fun recordResponseToRecordEntity(recordResponse: RecordResponse, billId: Int): RecordEntity {
        return recordResponse.toRecordEntity(billId)
    }

    private fun RecordResponse.toRecordEntity(billId: Int) = RecordEntity(
        id = this.id,
        name = this.name,
        sum = this.sum,
        type = this.type,
        color = this.color,
        icon = this.icon,
        date = this.date,
        billId = billId
    )

    fun billsWithRecordsListToBillsList(billsWithRecords: List<BillsWithRecordsRequest>): List<Bill> {
        return billsWithRecords.toBills()
    }

    private fun List<BillsWithRecordsRequest>.toBills() = this.map { it.toBill() }

    private fun BillsWithRecordsRequest.toBill() = Bill(
        id = this.billEntity.id,
        name = this.billEntity.name,
        amount = this.billEntity.amount,
        color = this.billEntity.color,
        records = this.records.toRecords()
    )

    private fun List<RecordEntity>.toRecords() = this.map { it.toRecord() }

    private fun RecordEntity.toRecord() = Record(
        id = this.id,
        name = this.name,
        sum = this.sum,
        type = this.type,
        color = this.color,
        icon = this.icon,
        date = this.date
    )

    /**
     * Bills list to BillUiModel list
     */
    fun billsWithRecordsListToBillsUiModelsList(bills: List<Bill>): List<BillItemUiModel.BillUiModel> {
        return bills.toBillUiModels()
    }

    fun billToBillUiModel(bill: Bill): BillItemUiModel.BillUiModel {
        return BillItemUiModel.BillUiModel(bill)
    }

    private fun List<Bill>.toBillUiModels() = this.map { BillItemUiModel.BillUiModel(it) }
}