package com.dima.financeapp.ui.main.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.ui.main.records.adapter.RecordAdapter
import kotlinx.android.synthetic.main.fragment_records.*

class RecordsFragment : Fragment() {

    companion object {
        private const val ARG_BILL = "bill_item"

        fun newInstance(
            bill: Bill
        ): RecordsFragment = RecordsFragment()
            .apply {
                arguments = bundleOf(
                    ARG_BILL to bill
                )
            }
    }

    private val recordAdapter = RecordAdapter { record ->

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bill = requireArguments().getParcelable<Bill>(ARG_BILL) as Bill

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = bill.name

        toolbar.navigationIcon =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        initViews(bill)
    }

    private fun initViews(bill: Bill) {
        recyclerRecords.layoutManager = LinearLayoutManager(activity)
        recordAdapter.bill = bill
        recordAdapter.submitList(bill.records)
        recyclerRecords.adapter = recordAdapter
    }
}