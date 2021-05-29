package com.dima.financeapp.ui.main.nav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.model.domain.Bill
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NavViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bills by lazy { MutableLiveData<List<Bill>>() }
    val bills: LiveData<List<Bill>>
        get() = _bills

    fun getUser() {
        compositeDisposable.add(
            financeInteractor.getUser()
                .subscribe({ user ->

                }, { throwable ->

                })
        )
    }

    fun getBills() {
        compositeDisposable.add(
            financeInteractor.getBills()
                .subscribe({ billsList ->
                    _bills.value = billsList
                }, { throwable ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}