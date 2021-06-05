package com.dima.financeapp.ui.main.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.CurrencyInteractor
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.model.net.currency.CurrencyRatesResponse
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor,
    private val currencyInteractor: CurrencyInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bills by lazy { MutableLiveData<List<BillItemUiModel.BillUiModel>>() }
    val bills: LiveData<List<BillItemUiModel.BillUiModel>>
        get() = _bills

    private val _currencyRates by lazy { MutableLiveData<CurrencyRatesResponse>() }
    val currencyRates: LiveData<CurrencyRatesResponse>
        get() = _currencyRates

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

    fun loadCurrencyRates() {
        compositeDisposable.add(
            currencyInteractor.loadCurrencyRates()
                .subscribe({ currencyRates ->
                    _currencyRates.value = currencyRates
                }, { throwable ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}