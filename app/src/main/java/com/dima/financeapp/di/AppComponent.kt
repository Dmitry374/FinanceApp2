package com.dima.financeapp.di

import com.dima.financeapp.ui.authorisation.LoginFragment
import com.dima.financeapp.ui.authorisation.RegistrationFragment
import com.dima.financeapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, ViewModelBuilderModule::class])
interface AppComponent {

    fun inject(loginFragment: LoginFragment)

    fun inject(registrationFragment: RegistrationFragment)

    fun inject(mainActivity: MainActivity)
}