package com.dima.financeapp.ui.main.currencyrates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.CurrencyInteractor
import com.dima.financeapp.model.net.currency.CurrencyRatesResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val currencyInteractor: CurrencyInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _currencyRates by lazy { MutableLiveData<CurrencyRatesResponse>() }
    val currencyRates: LiveData<CurrencyRatesResponse>
        get() = _currencyRates

    fun loadCurrencyRates() {
        compositeDisposable.add(
            currencyInteractor.loadCurrencyRates()
                .subscribe({ currencyRates ->

                }, { throwable ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}