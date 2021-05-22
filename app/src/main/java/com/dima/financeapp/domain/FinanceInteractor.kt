package com.dima.financeapp.domain

import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.network.request.AuthorisationRequestItem
import com.dima.financeapp.repository.AuthorisationRepository
import com.dima.financeapp.sharedpreference.SharedPreferenceHelper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FinanceInteractor(
    private val authorisationRepository: AuthorisationRepository,
    private val sharedPreferencesHelper: SharedPreferenceHelper
) {

    fun loginUser(authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse> {
        return authorisationRepository.loginUser(authorisationRequestItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun registerUser(authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse> {
        return authorisationRepository.registerUser(authorisationRequestItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}