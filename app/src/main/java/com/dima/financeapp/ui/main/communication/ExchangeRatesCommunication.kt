package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.net.currency.CurrencyRatesResponse

interface ExchangeRatesCommunication {
    fun displayCurrencyRates(currencyRatesResponse: CurrencyRatesResponse)
}