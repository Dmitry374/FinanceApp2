package com.dima.financeapp.ui.main.nav

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.ui.main.addbill.AddBillFragment
import com.dima.financeapp.ui.main.communication.MainTabCommunication
import com.dima.financeapp.ui.main.communication.NavFragmentCallback
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import javax.inject.Inject

class NavFragment : Fragment(), NavFragmentCallback {

    private val mainFragment = com.dima.financeapp.ui.main.main.MainFragment()
    private val addBillFragment = AddBillFragment()

    private var mainTabCommunication: MainTabCommunication? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val navViewModel: NavViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, mainFragment)
            .commit()

        initTabsCommunicationInterfaces()

        initObservers()

        navViewModel.getBills()
    }

    private fun initObservers() {
        navViewModel.bills.observe(viewLifecycleOwner, Observer(::displayBills))
    }

    private fun displayBills(bills: List<BillItemUiModel.BillUiModel>) {
        mainTabCommunication?.displayBills(bills)
    }

    private fun initTabsCommunicationInterfaces() {
        mainTabCommunication = mainFragment
    }

    override fun displayAddNewBillFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, addBillFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun displayNewBill(bill: BillItemUiModel.BillUiModel) {
        navViewModel.addNewBill(bill)
    }
}