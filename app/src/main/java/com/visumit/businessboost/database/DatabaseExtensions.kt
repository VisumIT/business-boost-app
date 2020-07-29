package com.visumit.businessboost.database

import android.content.ContentValues
import com.visumit.businessboost.model.Carrinho
import org.jetbrains.anko.db.update

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

fun CarrinhoDatabase.deletaTodosItensCarrinho(){
    val sql = "DELETE FROM TB_CARRINHO WHERE ID > 0"
    writableDatabase.execSQL(sql)
}

fun CarrinhoDatabase.atualizarQuantidade(carrinho: Carrinho): Int{

    val valores = ContentValues().apply {
        put("QUANTITY", carrinho.quantidade)
    }

    return writableDatabase.update("TB_CARRINHO", valores, "ID_PRODUCT=${carrinho.idProduct}", null)

}