package com.dima.financeapp.ui.main.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dima.financeapp.R
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import com.dima.financeapp.ui.main.communication.MainTabCommunication
import com.dima.financeapp.ui.main.main.billadapter.BillAdapter
import com.dima.financeapp.ui.main.main.billadapter.BillItemDecoration
import com.dima.financeapp.ui.main.main.billadapter.BillItemUiModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainTabCommunication {

    private var mainFragmentCommunicationInterface: MainFragmentCommunicationInterface? = null

    private val billAdapter = BillAdapter({ bill ->

    }, {
        mainFragmentCommunicationInterface?.displayAddNewBillFragment()
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainFragmentCommunicationInterface) {
            mainFragmentCommunicationInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val billItemDecoration = BillItemDecoration()
        recyclerMainBills.addItemDecoration(billItemDecoration)
        recyclerMainBills.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerMainBills.adapter = billAdapter
    }

    override fun displayBills(bills: List<BillItemUiModel.BillUiModel>) {
        billAdapter.submitList(bills)
    }
}