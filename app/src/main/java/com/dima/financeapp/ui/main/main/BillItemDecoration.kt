package com.dima.financeapp.ui.main.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dima.financeapp.R

class BillItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left =
            parent.context.resources.getDimensionPixelSize(R.dimen.margin_between_bills)
    }
}