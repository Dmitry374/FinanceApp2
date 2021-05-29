package com.dima.financeapp.di

import androidx.lifecycle.ViewModel
import com.dima.financeapp.ui.authorisation.viewmodel.AuthorisationViewModel
import com.dima.financeapp.ui.main.activity.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorisationViewModel::class)
    abstract fun bindAuthorisationViewModel(authorisationViewModel: AuthorisationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}