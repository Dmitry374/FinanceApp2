package com.dima.financeapp.common

import com.dima.financeapp.model.domain.User
import com.dima.financeapp.model.entity.UserEntity
import com.dima.financeapp.model.net.UserResponse

class DataMapper {

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
}