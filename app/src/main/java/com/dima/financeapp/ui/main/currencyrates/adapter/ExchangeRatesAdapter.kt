package com.dima.financeapp.ui.main.currencyrates.adapter

import android.annotation.SuppressLint
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
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants
import com.dima.financeapp.model.net.currency.Valuta
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import kotlin.math.round

class ExchangeRatesAdapter : RecyclerView.Adapter<ExchangeRatesAdapter.ExchangeRatesViewHolder>() {

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    private var currencyDate = Constants.EMPTY_STRING

    override fun getItemCount(): Int = differ.currentList.size

    fun setCurrencyDate(currencyDate: String) {
        this.currencyDate = currencyDate
    }

    fun submitList(list: List<Valuta>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ExchangeRatesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_exchange_rates, viewGroup, false)
        return ExchangeRatesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ExchangeRatesViewHolder, position: Int) {
        viewHolder.bind(differ.currentList[position], currencyDate)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Valuta>() {
            override fun areItemsTheSame(oldItem: Valuta, newItem: Valuta): Boolean {
                return oldItem.ID == newItem.ID
            }

            override fun areContentsTheSame(oldItem: Valuta, newItem: Valuta): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ExchangeRatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(valuta: Valuta, currencyDate: String) {

            val tvExchange = itemView.findViewById<TextView>(R.id.tvExchange)
            val tvCurrencyName = itemView.findViewById<TextView>(R.id.tvCurrencyName)
            val imgTypeDifference = itemView.findViewById<ImageView>(R.id.imgTypeDifference)
            val tvCurrencyDifference = itemView.findViewById<TextView>(R.id.tvCurrencyDifference)
            val tvDateExchange = itemView.findViewById<TextView>(R.id.tvDateExchange)

            val valute = round(10000.0 * (valuta.Value)) / 10000.0
            val previous = round(10000.0 * (valuta.Previous)) / 10000.0

            val difference: Double = round(10000.0 * (valute - previous)) / 10000.0


            tvExchange.text = "${valuta.Nominal} ${valuta.CharCode} = ${valuta.Value} руб."
            tvCurrencyName.text = valuta.Name
            tvCurrencyDifference.text = difference.toString()

            if (difference >= 0) {
                tvCurrencyDifference.setTextColor(
                    ContextCompat.getColor(
                        tvCurrencyDifference.context,
                        R.color.color_income
                    )
                )
                Glide.with(imgTypeDifference)
                    .load(R.mipmap.ic_dp)
                    .into(imgTypeDifference)
            } else {
                tvCurrencyDifference.setTextColor(
                    ContextCompat.getColor(
                        tvCurrencyDifference.context,
                        R.color.color_consumption
                    )
                )
                Glide.with(imgTypeDifference)
                    .load(R.mipmap.ic_dn)
                    .into(imgTypeDifference)
            }

            if (currencyDate.isNotEmpty()) {
                tvDateExchange.text = convertToNewFormat(currencyDate)
            }
        }

        private fun convertToNewFormat(dateStr: String): String {
            val utc = TimeZone.getTimeZone("UTC")
            val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale("ru"))  // Принимаем дату
            val destFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale("ru"))  // Конвертируем в
            sourceFormat.timeZone = utc
            val convertedDate = sourceFormat.parse(dateStr)
            return if (convertedDate == null) {
                Constants.EMPTY_STRING
            } else {
                destFormat.format(convertedDate)
            }
        }
    }
}