package com.visumit.businessboost

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.visumit.businessboost.model.Carrinho
import com.visumit.businessboost.model.Product
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.toast

class CarrinhoViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val textViewName = itemView.findViewById<TextView>(R.id.carrinho_item_nome)
    private val textViewPrice = itemView.findViewById<TextView>(R.id.carrinho_item_price)
    private val textViewQuantity = itemView.findViewById<TextView>(R.id.carrinho_item_quantidade)
    private val textViewPriceTotal = itemView.findViewById<Button>(R.id.carrinho_item_total)

    fun bind(item: Carrinho) {

    }

}