package com.dima.financeapp.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.utils.Event
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _isSignIn by lazy { MutableLiveData<Event<Boolean>>() }
    val isSignIn: LiveData<Event<Boolean>>
        get() = _isSignIn

    fun signIn() {
        if (financeInteractor.isUserSignIn()) {
            compositeDisposable.add(
                financeInteractor.loadUserData()
                    .subscribe({
                        _isSignIn.value = Event(true)
                    }, {
                        _isSignIn.value = Event(false)
                    })
            )
        } else {
            _isSignIn.value = Event(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}