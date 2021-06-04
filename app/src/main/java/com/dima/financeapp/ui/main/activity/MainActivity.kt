package com.dima.financeapp.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Category
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.ui.main.addbill.AddBillFragment
import com.dima.financeapp.ui.main.addrecord.AddRecordFragment
import com.dima.financeapp.ui.main.addrecord.categories.CategoriesFragment
import com.dima.financeapp.ui.main.communication.CategoryFragmentCommunication
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import com.dima.financeapp.ui.main.communication.MainTabCommunication
import com.dima.financeapp.ui.main.communication.RecordsFragmentCommunication
import com.dima.financeapp.ui.main.currencyrates.ExchangeRatesFragment
import com.dima.financeapp.ui.main.main.MainFragment
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import com.dima.financeapp.ui.main.records.RecordsFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainFragmentCommunicationInterface {

    private val mainFragment = MainFragment()
    private val addBillFragment = AddBillFragment()
    private val categoriesFragment = CategoriesFragment()
    private val recordsFragment = RecordsFragment()
    private val addRecordFragment = AddRecordFragment()
    private val exchangeRatesFragment = ExchangeRatesFragment()

    private var mainTabCommunication: MainTabCommunication? = null
    private var categoryFragmentCommunication: CategoryFragmentCommunication? = null
    private var recordsFragmentCommunication: RecordsFragmentCommunication? = null

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

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_main -> {
                    replaceFragment(mainFragment)
                    true
                }
                R.id.nav_exchange_rates -> {
                    replaceFragment(exchangeRatesFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun initObservers() {
        mainViewModel.bills.observe(this, Observer(::displayBills))
    }

    private fun displayBills(bills: List<BillItemUiModel.BillUiModel>) {
        mainTabCommunication?.displayBills(bills)
    }

    private fun initTabsCommunicationInterfaces() {
        mainTabCommunication = mainFragment
        categoryFragmentCommunication = addRecordFragment
        recordsFragmentCommunication = recordsFragment
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, fragment)
            .commit()
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

    override fun displayBillRecords(bill: Bill) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, recordsFragment)
            .addToBackStack(null)
            .commit()

        recordsFragmentCommunication?.setBill(bill)
    }

    override fun displayNewRecord(record: Record) {
        recordsFragmentCommunication?.displayNewRecord(record)
    }

    override fun onAddRecordScreen(bill: Bill) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, addRecordFragment)
            .addToBackStack(null)
            .commit()

        categoryFragmentCommunication?.setBill(bill)
    }

    override fun onCategoriesScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_container, categoriesFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSelectCategory(category: Category) {
        categoryFragmentCommunication?.selectCategory(category)
    }
}