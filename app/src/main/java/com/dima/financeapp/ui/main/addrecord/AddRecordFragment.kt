package com.dima.financeapp.ui.main.addrecord

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants
import com.dima.financeapp.common.hideKeyboard
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Category
import com.dima.financeapp.ui.main.communication.CategoryFragmentCommunication
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import kotlinx.android.synthetic.main.fragment_add_record.*

class AddRecordFragment : Fragment(), CategoryFragmentCommunication {

    private var bill: Bill? = null
    private var category: Category? = null

    private lateinit var recordTypes: Array<String>

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
        return inflater.inflate(R.layout.fragment_add_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recordTypes = resources.getStringArray(R.array.record_type)

        initToolbar(view)

        initSpinner()

        selectCategory()
    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = resources.getString(R.string.add_record_title)

        toolbar.navigationIcon =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null)

        toolbar.setNavigationOnClickListener {
            hideSoftKeyBoardAndClearText()
            requireActivity().onBackPressed()
        }
    }

    private fun initSpinner() {
        spinnerTypeRecord.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (recordTypes[position] == Constants.RECORD_TYPE_INCOME) {
                    tvSignEditBill.text = "+"
                } else {
                    tvSignEditBill.text = "-"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }
    }

    private fun selectCategory() {
        selectCategory.setOnClickListener {
            hideKeyboard()
            mainFragmentCommunicationInterface?.onCategoriesScreen()
        }
    }

    override fun setBill(bill: Bill) {
        this.bill = bill
    }

    override fun selectCategory(category: Category) {
        this.category = category
        tvFromBill.text = category.name
    }

    private fun hideKeyboard() {
        edSumNewRecord.hideKeyboard()
    }

    private fun hideSoftKeyBoardAndClearText() {
        edSumNewRecord.hideKeyboard()
        edSumNewRecord.text.clear()
    }
}