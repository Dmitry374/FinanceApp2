package com.dima.financeapp.ui.main.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dima.financeapp.R
import com.dima.financeapp.ui.main.communication.MainTabCommunication
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainTabCommunication {

    private val billAdapter = BillAdapter({ billUiModel ->

    }, {

    })

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
        recyclerMainBills.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerMainBills.adapter = billAdapter
    }

    override fun displayBills(bills: List<BillItemUiModel.BillUiModel>) {
        billAdapter.submitList(bills)
    }
}