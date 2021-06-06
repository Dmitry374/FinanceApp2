package com.dima.financeapp.ui.main.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dima.financeapp.domain.FinanceInteractor
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.network.request.AddRecordRequestItem
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import com.dima.financeapp.utils.Event
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainTabViewModel @Inject constructor(
    private val financeInteractor: FinanceInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bill = MutableLiveData<Event<BillItemUiModel.BillUiModel>>()
    val bill: LiveData<Event<BillItemUiModel.BillUiModel>>
        get() = _bill

    private val _records by lazy { MutableLiveData<List<Record>>() }
    val records: LiveData<List<Record>>
        get() = _records

    private val _record = MutableLiveData<Event<Record>>()
    val record: LiveData<Event<Record>>
        get() = _record

    fun addBill(name: String, amount: Double, color: Int) {
        compositeDisposable.add(
            financeInteractor.addBill(name, amount, color)
                .subscribe({ bill ->
                    _bill.value = Event(bill)
                }, { throwable ->

                })
        )
    }

    fun addRecord(addRecordRequestItem: AddRecordRequestItem, bill: Bill) {
        compositeDisposable.add(
            financeInteractor.addRecord(addRecordRequestItem, bill)
                .subscribe({ record ->
                    _record.value = Event(record)
                }, { throwable ->

                })
        )
    }

    fun getLastRecords(count: Int) {
        compositeDisposable.add(
            financeInteractor.getLastRecords(count)
                .subscribe({ recordsList ->
                    _records.value = recordsList
                }, { throwable ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}