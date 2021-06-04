package com.dima.financeapp.network

import com.dima.financeapp.model.net.currency.CurrencyRatesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyApiService {

    @GET("daily_json.js")
    fun loadCurrencyRates(): Single<CurrencyRatesResponse>
}