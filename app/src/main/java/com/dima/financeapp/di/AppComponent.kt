package com.dima.financeapp.di

import com.dima.financeapp.ui.authorisation.LoginFragment
import com.dima.financeapp.ui.authorisation.RegistrationFragment
import com.dima.financeapp.ui.main.activity.MainActivity
import com.dima.financeapp.ui.main.addbill.AddBillFragment
import com.dima.financeapp.ui.main.addrecord.AddRecordFragment
import com.dima.financeapp.ui.main.currencyrates.ExchangeRatesFragment
import com.dima.financeapp.ui.main.main.MainFragment
import com.dima.financeapp.ui.start.StartActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, ViewModelBuilderModule::class])
interface AppComponent {

    fun inject(startActivity: StartActivity)

    fun inject(loginFragment: LoginFragment)

    fun inject(registrationFragment: RegistrationFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

    fun inject(addBillFragment: AddBillFragment)

    fun inject(addRecordFragment: AddRecordFragment)

    fun inject(exchangeRatesFragment: ExchangeRatesFragment)
}