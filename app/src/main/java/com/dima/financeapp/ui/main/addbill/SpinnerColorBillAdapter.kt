package com.dima.financeapp.ui.main.addbill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.cardview.widget.CardView
import com.dima.financeapp.R

class SpinnerColorBillAdapter(private val colors: IntArray) : BaseAdapter() {

    override fun getCount(): Int {
        return colors.size
    }

    override fun getItem(position: Int): Any {
        return colors[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val viewHolder: ColorViewHolder?
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.spinner_color_bill, parent, false)
            viewHolder = ColorViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = if (view.tag is ColorViewHolder) {
                view.tag as ColorViewHolder
            } else {
                null
            }
        }

        viewHolder?.bind(colors[position])

        return view
    }

    private class ColorViewHolder(private val itemView: View) {
        fun bind(color: Int) {
            val cardView = itemView.findViewById<CardView>(R.id.card_spinner_color_bill)
            cardView.setCardBackgroundColor(color)
        }
    }
}