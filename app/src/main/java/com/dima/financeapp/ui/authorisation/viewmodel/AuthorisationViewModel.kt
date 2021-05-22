package com.dima.financeapp.ui.authorisation.viewmodel

import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.AuthorisationInteractor
import javax.inject.Inject

class AuthorisationViewModel @Inject constructor(
    private val authorisationInteractor: AuthorisationInteractor
) : ViewModel() {
}