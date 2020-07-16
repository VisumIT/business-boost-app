package com.visumit.businessboost

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.visumit.businessboost.model.Product
import java.lang.String.format
import java.text.DecimalFormat

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {

    private val textViewPrice = itemView.findViewById<TextView>(R.id.product_price)
    private val textViewName = itemView.findViewById<TextView>(R.id.product_name)
    private val textViewPriceDiscount = itemView.findViewById<TextView>(R.id.product_price_discount)
    private val btnAddProduct = itemView.findViewById<Button>(R.id.btn_add_product)
    private  val imageProduct: ImageView = itemView.findViewById(R.id.product_img)

    fun bind(item: Product) {
        if (item.imagesUrl != "" && item.imagesUrl != null){
            Picasso.get().load(item.imagesUrl).into(imageProduct)

        }else{
            Picasso.get().load(R.mipmap.boost_icon)

        }

        textViewPrice.text = "R$ " + item.price.toString()
        textViewName.text = item.name.toString()

        // Aplicar o desconto no preço final
        var priceDiscount = item.price * (item.discount / 100 - 1) * -1
        textViewPriceDiscount.text = "R$ " + priceDiscount.toString()

        btnAddProduct.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       print("tambem")
    }


}