package com.dima.financeapp.di

import androidx.lifecycle.ViewModel
import com.dima.financeapp.ui.authorisation.viewmodel.AuthorisationViewModel
import com.dima.financeapp.ui.main.activity.MainViewModel
import com.dima.financeapp.ui.main.main.MainTabViewModel
import com.dima.financeapp.ui.start.StartViewModel
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
    abstract fun bindNavViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainTabViewModel::class)
    abstract fun bindMainTabViewModel(navViewModel: MainTabViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    abstract fun bindStartViewModel(startViewModel: StartViewModel): ViewModel
}