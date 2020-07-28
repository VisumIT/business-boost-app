package com.visumit.businessboost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.visumit.businessboost.model.Carrinho
import com.visumit.businessboost.model.Product

class CarrinhoAdapter : RecyclerView.Adapter<CarrinhoViewHolder>() {

    private var items = listOf<Carrinho>()

    fun updateItems(newItems: List<Carrinho>){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_carrinho, parent, false)

        return  CarrinhoViewHolder(view)
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        holder.bind(items[position])
    }

}