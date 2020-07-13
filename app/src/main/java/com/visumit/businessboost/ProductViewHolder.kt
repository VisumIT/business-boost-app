package com.visumit.businessboost

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.visumit.businessboost.model.Product

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textViewPrice = itemView.findViewById<TextView>(R.id.product_price)

    fun bind(item: Product) {
        textViewPrice.text = item.price.toString()
    }
}