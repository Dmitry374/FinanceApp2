package com.dima.financeapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Bill

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
    }
}