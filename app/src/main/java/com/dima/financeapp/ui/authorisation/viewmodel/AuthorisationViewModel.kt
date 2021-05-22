package com.dima.financeapp.ui.authorisation.viewmodel

import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.AuthorisationInteractor
import com.dima.financeapp.network.request.AuthorisationRequestItem
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AuthorisationViewModel @Inject constructor(
    private val authorisationInteractor: AuthorisationInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun loginUser(email: String, password: String) {
        compositeDisposable.add(
            authorisationInteractor.loginUser(
                AuthorisationRequestItem(
                    email = email,
                    password = password
                )
            )
                .subscribe({

                }, {

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}