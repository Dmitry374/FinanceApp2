package com.dima.financeapp.ui.main.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import com.dima.financeapp.ui.main.communication.MainTabCommunication
import com.dima.financeapp.ui.main.main.billadapter.BillAdapter
import com.dima.financeapp.ui.main.main.billadapter.BillItemDecoration
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import com.dima.financeapp.ui.main.records.adapter.RecordAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment(), MainTabCommunication {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainTabViewModel: MainTabViewModel by viewModels {
        viewModelFactory
    }

    private var mainFragmentCommunicationInterface: MainFragmentCommunicationInterface? = null

    private val billAdapter = BillAdapter({ bill ->
        mainFragmentCommunicationInterface?.displayBillRecords(bill)
    }, {
        mainFragmentCommunicationInterface?.displayAddNewBillFragment()
    })

    private val recordAdapter = RecordAdapter { record ->

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainFragmentCommunicationInterface) {
            mainFragmentCommunicationInterface = context
        }

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        mainTabViewModel.getLastRecords(Constants.LAST_RECORDS_COUNT)
    }

    private fun initViews() {
        val billItemDecoration = BillItemDecoration()
        recyclerMainBills.addItemDecoration(billItemDecoration)
        recyclerMainBills.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerMainBills.adapter = billAdapter

        recyclerLastRecord.layoutManager = LinearLayoutManager(activity)
        recordAdapter.bill = null
        recyclerLastRecord.adapter = recordAdapter
    }

    private fun initObservers() {
        mainTabViewModel.records.observe(viewLifecycleOwner, Observer(::displayRecords))
    }

    override fun displayBills(bills: List<BillItemUiModel.BillUiModel>) {
        billAdapter.submitList(bills)
    }

    private fun displayRecords(records: List<Record>) {
        recordAdapter.submitList(records)
    }
}