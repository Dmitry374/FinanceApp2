package com.dima.financeapp.domain

import com.dima.financeapp.model.net.currency.CurrencyRatesResponse
import com.dima.financeapp.repository.CurrencyRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyInteractor(
    private val currencyRepository: CurrencyRepository
) {

    fun loadCurrencyRates(): Single<CurrencyRatesResponse> {
        return currencyRepository.loadCurrencyRates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}