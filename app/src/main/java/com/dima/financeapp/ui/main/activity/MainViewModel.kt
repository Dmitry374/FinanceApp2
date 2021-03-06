package com.dima.financeapp.ui.main.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.CurrencyInteractor
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.model.domain.User
import com.dima.financeapp.model.net.currency.CurrencyRatesResponse
import com.dima.financeapp.network.request.UserEditRequest
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import com.dima.financeapp.utils.Event
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor,
    private val currencyInteractor: CurrencyInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _bills by lazy { MutableLiveData<List<BillItemUiModel.BillUiModel>>() }
    val bills: LiveData<List<BillItemUiModel.BillUiModel>>
        get() = _bills

    private val _currencyRates by lazy { MutableLiveData<CurrencyRatesResponse>() }
    val currencyRates: LiveData<CurrencyRatesResponse>
        get() = _currencyRates

    private val _updateUserDataSuccess by lazy { MutableLiveData<Event<Boolean>>() }
    val updateUserDataSuccess: LiveData<Event<Boolean>>
        get() = _updateUserDataSuccess

    private val _logOutSuccess by lazy { MutableLiveData<Event<Boolean>>() }
    val logOutSuccess: LiveData<Event<Boolean>>
        get() = _logOutSuccess

    fun getUser() {
        compositeDisposable.add(
            financeInteractor.getUser()
                .subscribe({ user ->
                    _user.value = user
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

    fun addNewBill(bill: BillItemUiModel.BillUiModel) {
        val billsList = _bills.value?.toMutableList()
        billsList?.add(bill)
        _bills.value = billsList
    }

    fun loadCurrencyRates() {
        compositeDisposable.add(
            currencyInteractor.loadCurrencyRates()
                .subscribe({ currencyRates ->
                    _currencyRates.value = currencyRates
                }, { throwable ->

                })
        )
    }

    fun editUser(userEditRequest: UserEditRequest) {
        compositeDisposable.add(
            financeInteractor.editUser(userEditRequest)
                .subscribe({
                    _updateUserDataSuccess.value = Event(true)
                }, {
                    _updateUserDataSuccess.value = Event(false)
                })
        )
    }

    fun logOut() {
        compositeDisposable.add(
            financeInteractor.clearAllDataFromDb()
                .subscribe({
                    _logOutSuccess.value = Event(true)
                }, {
                    _logOutSuccess.value = Event(false)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}