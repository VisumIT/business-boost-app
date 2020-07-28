package com.visumit.businessboost.database

import android.content.ContentValues
import com.visumit.businessboost.model.Carrinho

fun CarrinhoDatabase.inserir(item: Carrinho) : Long{

    val idCarrinho = writableDatabase.insert("TB_CARRINHO", null, ContentValues().apply {
        put("NAME", item.name)
        put("ID_PRODUCT", item.idProduct)
        put("QUANTITY", item.quantidade)
        put("TOTAL_PRICE", item.totalPrice)
    })

    return idCarrinho
}