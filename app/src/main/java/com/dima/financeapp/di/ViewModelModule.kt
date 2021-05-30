package com.dima.financeapp.di

import androidx.lifecycle.ViewModel
import com.dima.financeapp.ui.authorisation.viewmodel.AuthorisationViewModel
import com.dima.financeapp.ui.main.main.MainTabViewModel
import com.dima.financeapp.ui.main.nav.NavViewModel
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
    @ViewModelKey(NavViewModel::class)
    abstract fun bindNavViewModel(navViewModel: NavViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainTabViewModel::class)
    abstract fun bindMainTabViewModel(navViewModel: MainTabViewModel): ViewModel
}