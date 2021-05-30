package com.dima.financeapp.ui.main.nav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NavViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bills by lazy { MutableLiveData<List<BillItemUiModel.BillUiModel>>() }
    val bills: LiveData<List<BillItemUiModel.BillUiModel>>
        get() = _bills

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
                .subscribe({ billsList ->
                    _bills.value = billsList
                }, { throwable ->

                })
        )
    }

    fun addNewBill(bill: BillItemUiModel.BillUiModel) {
        val billsList = _bills.value?.toMutableList()
        billsList?.add(bill)
        _bills.value = billsList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}