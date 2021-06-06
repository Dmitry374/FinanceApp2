package com.dima.financeapp.ui.main.main.billadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Bill
import com.github.ivbaranov.mli.MaterialLetterIcon

class BillAdapter(
    private val clickBillListener: (Bill) -> Unit,
    private val clickAddNewBillListener: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position]) {
            is BillItemUiModel.BillUiModel -> BILL_TYPE
            is BillItemUiModel.AddNewBillUiModel -> ADD_NEW_BILL_TYPE
        }
    }

    fun submitList(bills: List<BillItemUiModel>) {
        val list = mutableListOf<BillItemUiModel>()
        list.add(BillItemUiModel.AddNewBillUiModel)
        list.addAll(bills)
        differ.submitList(list)
    }

    fun getItems(): MutableList<BillItemUiModel> {
        return differ.currentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BILL_TYPE -> {
                BillViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_bill, parent, false)
                ) { position ->
                    val billUiModel = differ.currentList[position]
                    if (billUiModel is BillItemUiModel.BillUiModel) {
                        clickBillListener(billUiModel.bill)
                    }
                }
            }
            ADD_NEW_BILL_TYPE -> {
                AddNewBillViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_add_new_bill, parent, false)
                ) {
                    clickAddNewBillListener.invoke()
                }
            }
            else -> throw IllegalArgumentException("Unsupported post or comment view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        differ.currentList[position].let {
            when (it) {
                is BillItemUiModel.BillUiModel -> (holder as BillViewHolder).bind(it.bill)
                is BillItemUiModel.AddNewBillUiModel -> (holder as AddNewBillViewHolder).bind()
            }
        }
    }

    companion object {

        private const val BILL_TYPE = 0
        private const val ADD_NEW_BILL_TYPE = 1

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BillItemUiModel>() {
            override fun areItemsTheSame(oldItem: BillItemUiModel, newItem: BillItemUiModel): Boolean {
                return oldItem is BillItemUiModel.AddNewBillUiModel == newItem is BillItemUiModel.AddNewBillUiModel ||
                    oldItem is BillItemUiModel.BillUiModel == newItem is BillItemUiModel.BillUiModel &&
                    (oldItem as BillItemUiModel.BillUiModel).bill.id == (newItem as BillItemUiModel.BillUiModel).bill.id
            }

            override fun areContentsTheSame(oldItem: BillItemUiModel, newItem: BillItemUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    class BillViewHolder(
        itemView: View,
        private val onBillClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(bill: Bill) {

            val listColors = itemView.resources.getIntArray(R.array.colorSpinnerBill)

            val billLetterIcon = itemView.findViewById<MaterialLetterIcon>(R.id.billLetterIcon)

            billLetterIcon.apply {
                shapeColor = listColors[bill.color]
                shapeType = MaterialLetterIcon.Shape.CIRCLE
                letter = bill.name.first().toString()
                letterSize = 18
                lettersNumber = 1
            }

            itemView.setOnClickListener { onBillClick(absoluteAdapterPosition) }
            itemView.findViewById<TextView>(R.id.textViewBillName).text = bill.name
            itemView.findViewById<TextView>(R.id.textViewBillAmount).text =
                String.format(itemView.context.getString(R.string.bill_amount_in_rubles), bill.amount)
        }
    }

    class AddNewBillViewHolder(
        itemView: View,
        private val onAddNewBillClick: () -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            itemView.setOnClickListener { onAddNewBillClick.invoke() }
        }
    }
}