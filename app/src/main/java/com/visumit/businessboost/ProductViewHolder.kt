package com.visumit.businessboost

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.visumit.businessboost.database.CarrinhoDatabase
import com.visumit.businessboost.database.atualizarQuantidade
import com.visumit.businessboost.database.inserir
import com.visumit.businessboost.database.listarProdutosCarrinho
import com.visumit.businessboost.model.Carrinho
import com.visumit.businessboost.model.Product
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.toast

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val textViewPrice = itemView.findViewById<TextView>(R.id.product_price)
    private val textViewName = itemView.findViewById<TextView>(R.id.product_name)
    private val textViewPriceDiscount = itemView.findViewById<TextView>(R.id.product_price_discount)
    private val btnAddProduct = itemView.findViewById<Button>(R.id.btn_add_product)
    private  val imageProduct: ImageView = itemView.findViewById(R.id.product_img)
    //private lateinit var database: CarrinhoDatabase

    fun bind(item: Product, context: Context) {
        var database = CarrinhoDatabase(context)
        if (item.imagesUrl != "" && item.imagesUrl != null){
            Picasso.get().load(item.imagesUrl).into(imageProduct)

        }else{
            Picasso.get().load(R.mipmap.boost_icon)
        }

        textViewPrice.text = "R$ " + String.format("%.2f", item.price).replace(".", ",")
        textViewName.text = item.name.toString()

        // Aplicar o desconto no preço final
        var priceDiscount = item.price * (item.discount / 100 - 1) * -1
        textViewPriceDiscount.text = "R$ " + String.format("%.2f", priceDiscount).replace(".", ",")




        btnAddProduct.setOnClickListener {

            var verificarList = database.listarProdutosCarrinho()





            val preferences = UserPreferences()
            var token = preferences.getToken(context)

            val carrinho = Carrinho(
                idProduct = item.id.toInt(),
                name = item.name.toString(),
                quantidade = 1,
                totalPrice = item.totalPrice.toDouble(),
                imgUrl = item.imagesUrl
            )

            var ref = true
            for (elem in verificarList){
                if (elem.idProduct == item.id) {
                    database.atualizarQuantidade(Carrinho(
                        idProduct = item.id,
                        name = item.name.toString(),
                        quantidade = elem.quantidade + 1,
                        totalPrice = item.totalPrice.toDouble(),
                        imgUrl = item.imagesUrl
                    ))
                    ref = false
                    context.toast("Quantidade aumentada: ${elem.quantidade}!")
                }

            }

            if (ref){
                database = CarrinhoDatabase(context)
                val idCarrinho = database.inserir(carrinho)
                if(idCarrinho == -1L){
                    context.toast("Erro ao inserir o produto")
                }else{
                    context.toast("Produto inserido no carrinho!")
                }
            }


        }


    }




}