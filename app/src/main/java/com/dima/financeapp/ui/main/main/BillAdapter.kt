package com.dima.financeapp.ui.main.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Bill

class BillAdapter(
    private val clickBillListener: (Bill) -> Unit
) : RecyclerView.Adapter<BillAdapter.BillViewHolder>() {

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(bills: List<Bill>) {
        differ.submitList(bills)
    }

    fun getItems(): MutableList<Bill> {
        return differ.currentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        return BillViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bill, parent, false)
        ) { position ->
            clickBillListener(differ.currentList[position])
        }
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val bill = differ.currentList[position]
        holder.bind(bill)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bill>() {
            override fun areItemsTheSame(oldItem: Bill, newItem: Bill): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Bill, newItem: Bill): Boolean {
                return oldItem == newItem
            }
        }
    }

    class BillViewHolder(
        itemView: View,
        private val onBillClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(bill: Bill) {

            itemView.setOnClickListener { onBillClick(absoluteAdapterPosition) }
            itemView.findViewById<TextView>(R.id.textViewBillName).text = bill.name
            itemView.findViewById<TextView>(R.id.textViewBillAmount).text =
                String.format(itemView.context.getString(R.string.bill_amount_in_rubles), bill.amount)
        }
    }
}