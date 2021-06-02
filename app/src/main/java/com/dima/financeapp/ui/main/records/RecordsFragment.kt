package com.dima.financeapp.ui.main.records

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Record
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import com.dima.financeapp.ui.main.communication.RecordsFragmentCommunication
import com.dima.financeapp.ui.main.records.adapter.RecordAdapter
import kotlinx.android.synthetic.main.fragment_records.*

class RecordsFragment : Fragment(), RecordsFragmentCommunication {

    private var bill: Bill? = null

    private val recordAdapter = RecordAdapter { record ->

    }

    private var mainFragmentCommunicationInterface: MainFragmentCommunicationInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainFragmentCommunicationInterface) {
            mainFragmentCommunicationInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bill?.let { bill ->
            val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
            toolbar.title = bill.name

            toolbar.navigationIcon =
                ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null)

            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }

            initViews(bill)
        }
    }

    private fun initViews(bill: Bill) {
        recyclerRecords.layoutManager = LinearLayoutManager(activity)
        recordAdapter.bill = bill
        recordAdapter.submitList(bill.records)
        recyclerRecords.adapter = recordAdapter

        createNewRecord.setOnClickListener {
            mainFragmentCommunicationInterface?.onAddRecordScreen(bill)
        }
    }

    override fun setBill(bill: Bill) {
        this.bill = bill
    }

    override fun displayNewRecord(record: Record) {
        val currentRecords = recordAdapter.getItems().toMutableList()
        currentRecords.add(record)
        recordAdapter.submitList(currentRecords)
    }
}