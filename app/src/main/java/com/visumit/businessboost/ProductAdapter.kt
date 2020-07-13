package com.visumit.businessboost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.visumit.businessboost.model.Product

class ProductAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private var items = listOf<Product>()

    fun updateItems(newItems: List<Product>){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return  ProductViewHolder(view)
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

}