package com.dima.financeapp.ui.main.addrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Bill

class AddRecordFragment : Fragment() {

    companion object {
        private const val ARG_BILL = "bill_item"

        fun newInstance(
            bill: Bill
        ): AddRecordFragment = AddRecordFragment()
            .apply {
                arguments = bundleOf(
                    ARG_BILL to bill
                )
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_record, container, false)
    }
}