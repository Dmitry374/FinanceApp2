package com.dima.financeapp.ui.main.main

import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainTabViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addBill(name: String, amount: Double) {
        compositeDisposable.add(
            financeInteractor.addBill(name, amount)
                .subscribe({ bill ->

                }, { throwable ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}