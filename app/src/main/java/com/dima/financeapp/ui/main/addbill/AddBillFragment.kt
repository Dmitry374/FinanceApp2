package com.dima.financeapp.ui.main.addbill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.dima.financeapp.R
import com.dima.financeapp.common.hideKeyboard
import kotlinx.android.synthetic.main.fragment_add_bill.*

class AddBillFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addBillToolbar = view.findViewById<Toolbar>(R.id.toolbar)
        addBillToolbar.title = resources.getString(R.string.add_bill_title)

        addBillToolbar.navigationIcon =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null)

        addBillToolbar.setNavigationOnClickListener {
            hideSoftKeyBoardAndClearText()
            requireActivity().onBackPressed()
        }

        addBillToolbar.inflateMenu(R.menu.add_bill_menu)

        addBillToolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_bill -> {

                    return@OnMenuItemClickListener true
                }
                else -> {
                    return@OnMenuItemClickListener true
                }
            }
        })
    }

    private fun hideSoftKeyBoardAndClearText() {
        edNameBill.hideKeyboard()
        edNameBill.text.clear()

        edInitialValueBill.hideKeyboard()
        edInitialValueBill.text.clear()
    }
}