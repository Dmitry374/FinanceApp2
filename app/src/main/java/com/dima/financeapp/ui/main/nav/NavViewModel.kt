package com.dima.financeapp.ui.main.nav

import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NavViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getUser() {
        compositeDisposable.add(
            financeInteractor.getUser()
                .subscribe({ user ->

                }, { throwable ->

                })
        )
    }

    fun getBills() {
        compositeDisposable.add(
            financeInteractor.getBills()
                .subscribe({ bills ->

                }, { throwable ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}