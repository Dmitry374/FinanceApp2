package com.dima.financeapp.common

import com.dima.financeapp.model.domain.User
import com.dima.financeapp.model.entity.BillEntity
import com.dima.financeapp.model.entity.RecordEntity
import com.dima.financeapp.model.entity.UserEntity
import com.dima.financeapp.model.net.BillResponse
import com.dima.financeapp.model.net.RecordResponse
import com.dima.financeapp.model.net.UserResponse

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

    private fun BillResponse.toBillEntity() = BillEntity(
        id = this.id,
        name = this.name,
        amount = this.amount,
        color = this.color,
    )

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
}