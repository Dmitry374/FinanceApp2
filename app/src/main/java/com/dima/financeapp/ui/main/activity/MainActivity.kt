package com.dima.financeapp.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.ui.main.addbill.AddBillFragment
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import com.dima.financeapp.ui.main.communication.MainTabCommunication
import com.dima.financeapp.ui.main.main.MainFragment
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainFragmentCommunicationInterface {

    private val mainFragment = MainFragment()
    private val addBillFragment = AddBillFragment()

    private var mainTabCommunication: MainTabCommunication? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_container, mainFragment)
                .commit()
        }

        initTabsCommunicationInterfaces()

        initObservers()

        mainViewModel.getBills()
    }

    private fun initObservers() {
        mainViewModel.bills.observe(this, Observer(::displayBills))
    }

    private fun displayBills(bills: List<BillItemUiModel.BillUiModel>) {
        mainTabCommunication?.displayBills(bills)
    }

    private fun initTabsCommunicationInterfaces() {
        mainTabCommunication = mainFragment
    }

    override fun displayAddNewBillFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, addBillFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun displayNewBill(bill: BillItemUiModel.BillUiModel) {
        mainViewModel.addNewBill(bill)
    }
}