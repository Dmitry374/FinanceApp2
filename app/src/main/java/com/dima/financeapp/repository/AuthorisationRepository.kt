package com.dima.financeapp.repository

import com.dima.financeapp.common.DataMapper
import com.dima.financeapp.database.FinanceDao
import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.network.ApiService
import com.dima.financeapp.network.request.AuthorisationRequestItem
import io.reactivex.Single

class AuthorisationRepository(
    private val apiService: ApiService,
    private val financeDao: FinanceDao,
    private val dataMapper: DataMapper
) {

    fun loginUser(authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse> {
        return apiService.loginUser(authorisationRequestItem)
    }

    fun registerUser(authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse> {
        return apiService.registerUser(authorisationRequestItem)
    }
}