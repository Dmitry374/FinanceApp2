package com.dima.financeapp.ui.main.records.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants
import com.dima.financeapp.common.getDateText
import com.dima.financeapp.model.domain.Bill
import com.dima.financeapp.model.domain.Record

class RecordAdapter(
    private val clickRecordListener: (Record) -> Unit
) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    var bill: Bill? = null

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun getItems(): List<Record> {
        return differ.currentList
    }

    fun submitList(list: List<Record>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_record, parent, false),
            bill
        ) { position ->
            clickRecordListener(differ.currentList[position])
        }
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem == newItem
            }
        }
    }

    class RecordViewHolder(
        itemView: View,
        private val bill: Bill?,
        private val onRecordClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(record: Record) {

            itemView.setOnClickListener { onRecordClick(absoluteAdapterPosition) }

            val imgCategoryRecycler = itemView.findViewById<ImageView>(R.id.imgCategoryRecycler)
            val tvNameCategoryRecycler = itemView.findViewById<TextView>(R.id.tvNameCategoryRecycler)
            val tvNameBillRecycler = itemView.findViewById<TextView>(R.id.tvNameBillRecycler)
            val tvSumOperation = itemView.findViewById<TextView>(R.id.tvSumOperation)
            val tvDateOperation = itemView.findViewById<TextView>(R.id.tvDateOperation)

            Glide.with(imgCategoryRecycler)
                .load(record.icon)
                .apply(
                    RequestOptions().override(
                        Constants.RECORD_TYPE_IMAGE_SIZE,
                        Constants.RECORD_TYPE_IMAGE_SIZE
                    )
                )
                .circleCrop()
                .into(imgCategoryRecycler)

            tvNameCategoryRecycler.text = record.name
            tvNameBillRecycler.text = bill?.name

            if (record.type == Constants.RECORD_TYPE_CONSUMPTION) {  // Если тип - расход
                tvSumOperation.setTextColor(ContextCompat.getColor(tvSumOperation.context, R.color.color_consumption))
                tvSumOperation.text =
                    String.format(itemView.context.getString(R.string.consumption_sum_in_rubles), record.sum)
            } else {
                tvSumOperation.setTextColor(ContextCompat.getColor(tvSumOperation.context, R.color.color_income))
                tvSumOperation.text =
                    String.format(itemView.context.getString(R.string.income_sum_in_rubles), record.sum)
            }
            tvDateOperation.text = record.date.getDateText()
        }
    }
}