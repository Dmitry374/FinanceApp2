package com.dima.financeapp.ui.main.addrecord.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants
import com.dima.financeapp.model.domain.Category

class CategoryAdapter(
    private val categories: List<Category>,
    private val clickCategoryListener: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun getItemCount(): Int = categories.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_category, viewGroup, false)
        return CategoryViewHolder(view) { position ->
            clickCategoryListener(categories[position])
        }
    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.bind(categories[position])
    }

    class CategoryViewHolder(
        itemView: View,
        private val onCategoryClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {

            itemView.setOnClickListener { onCategoryClick(absoluteAdapterPosition) }

            val imgCategory = itemView.findViewById<ImageView>(R.id.imgCategory)
            val tvNameCategory = itemView.findViewById<TextView>(R.id.tvNameCategory)

            tvNameCategory.text = category.name

            Glide.with(imgCategory)
                .load(category.icon)
                .apply(
                    RequestOptions().override(
                        Constants.RECORD_TYPE_IMAGE_SIZE,
                        Constants.RECORD_TYPE_IMAGE_SIZE
                    )
                )
                .circleCrop()
                .into(imgCategory)
        }
    }
}