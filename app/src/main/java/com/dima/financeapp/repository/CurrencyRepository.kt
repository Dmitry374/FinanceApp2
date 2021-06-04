package com.dima.financeapp.repository

import com.dima.financeapp.model.net.currency.CurrencyRatesResponse
import com.dima.financeapp.network.CurrencyApiService
import io.reactivex.Single

class CurrencyRepository(
    private val currencyApiService: CurrencyApiService
) {

    fun loadCurrencyRates(): Single<CurrencyRatesResponse> {
        return currencyApiService.loadCurrencyRates()
    }
}