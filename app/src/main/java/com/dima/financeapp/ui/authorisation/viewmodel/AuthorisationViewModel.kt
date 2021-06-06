package com.dima.financeapp.ui.authorisation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.network.request.AuthorisationRequestItem
import com.dima.financeapp.network.request.RegistrationRequestItem
import com.dima.financeapp.utils.Event
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AuthorisationViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _loginSuccess by lazy { MutableLiveData<Event<Boolean>>() }
    val loginSuccess: LiveData<Event<Boolean>>
        get() = _loginSuccess

    private val _registerSuccess by lazy { MutableLiveData<Event<Boolean>>() }
    val registerSuccess: LiveData<Event<Boolean>>
        get() = _registerSuccess

    fun loginUser(email: String, password: String) {
        compositeDisposable.add(
            financeInteractor.loginUser(
                AuthorisationRequestItem(
                    email = email,
                    password = password
                )
            )
                .subscribe({
                    _loginSuccess.value = Event(true)
                }, {
                    _loginSuccess.value = Event(false)
                })
        )
    }

    fun registrationUser(name: String, email: String, password: String) {
        compositeDisposable.add(
            financeInteractor.registerUser(
                RegistrationRequestItem(
                    name = name,
                    email = email,
                    password = password
                )
            )
                .subscribe({
                    _registerSuccess.value = Event(true)
                }, { throwable ->
                    _registerSuccess.value = Event(false)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}