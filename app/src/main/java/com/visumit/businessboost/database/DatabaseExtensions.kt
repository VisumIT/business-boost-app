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

fun CarrinhoDatabase.listarProdutosCarrinho(): List<Carrinho>{

    val sql = "SELECT * FROM TB_CARRINHO"
    val cursor = readableDatabase.rawQuery(sql, null)

    val listProductsCarrinho = mutableListOf<Carrinho>()
    if (cursor.count > 0){

        while (cursor.moveToNext()){
            val carrinho = Carrinho(
                id = cursor.getInt(cursor.getColumnIndex("ID")),
                idProduct = cursor.getInt(cursor.getColumnIndex("ID_PRODUCT")),
                name = cursor.getString(cursor.getColumnIndex("NAME")),
                quantidade = cursor.getInt(cursor.getColumnIndex("QUANTITY")),
                totalPrice = cursor.getDouble(cursor.getColumnIndex("TOTAL_PRICE"))
            )

            listProductsCarrinho.add(carrinho)
        }
        cursor.close()
    }

    return listProductsCarrinho
}