package com.visumit.businessboost

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
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
    private val textViewPriceTotal = itemView.findViewById<TextView>(R.id.carrinho_item_total)
    private val imageViewProduct = itemView.findViewById<ImageView>(R.id.img_carrinho)
    private val editTextQuantidade = itemView.findViewById<EditText>(R.id.txt_quantidade)

    fun bind(item: Carrinho) {

        if (item.imgUrl != "" && item.imgUrl != null){
            Picasso.get().load(item.imgUrl).into(imageViewProduct)

        }else{
            Picasso.get().load(R.mipmap.boost_icon)
        }
        editTextQuantidade.setText(item.quantidade.toString())
        textViewName.text = item.name.toString()
        textViewPrice.text = "Preço Unitario R$ " + String.format("%.2f", item.totalPrice)
        textViewQuantity.text = "Qt: " + item.quantidade.toString()


        var totalPrice = item.totalPrice.toDouble() * item.quantidade.toDouble()
        textViewPriceTotal.text = "Preço total R$ " + String.format("%.2f", totalPrice)
    }

}