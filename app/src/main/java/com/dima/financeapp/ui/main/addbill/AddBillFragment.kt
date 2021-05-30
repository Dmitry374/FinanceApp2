package com.dima.financeapp.ui.main.addbill

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.common.hideKeyboard
import com.dima.financeapp.ui.main.main.MainTabViewModel
import kotlinx.android.synthetic.main.fragment_add_bill.*
import javax.inject.Inject

class AddBillFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainTabViewModel: MainTabViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

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
                    val name = edNameBill.text.toString()
                    val amountText = edInitialValueBill.text.toString()
                    if (name.isNotEmpty() && amountText.isNotEmpty()) {
                        mainTabViewModel.addBill(name, amountText.toDouble())
                    }
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