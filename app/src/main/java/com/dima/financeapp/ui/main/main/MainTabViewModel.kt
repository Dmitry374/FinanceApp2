package com.dima.financeapp.ui.main.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import com.dima.financeapp.utils.Event
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainTabViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bill = MutableLiveData<Event<BillItemUiModel.BillUiModel>>()
    val bill: LiveData<Event<BillItemUiModel.BillUiModel>>
        get() = _bill

    fun addBill(name: String, amount: Double) {
        compositeDisposable.add(
            financeInteractor.addBill(name, amount)
                .subscribe({ bill ->
                    _bill.value = Event(bill)
                }, { throwable ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}