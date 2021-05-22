package com.dima.financeapp.di

import androidx.lifecycle.ViewModel
import com.dima.financeapp.ui.authorisation.viewmodel.AuthorisationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorisationViewModel::class)
    abstract fun bindGithubUsersViewModel(authorisationViewModel: AuthorisationViewModel): ViewModel
}