package com.visumit.businessboost.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CarrinhoDatabase(context: Context) : SQLiteOpenHelper(context, "carrinho.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            |CREATE TABLE TB_CARRINHO(
            |   ID INTEGER PRIMARY KEY AUTOINCREMENT,
            |   ID_PRODUCT INTEGER ,
            |   QUANTITY INTEGER,
            |   IMG_URL TEXT,
            |   NAME TEXT,
            |   TOTAL_PRICE DOUBLE
            |);
        |"""".trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS TB_CARRINHO")
        onCreate(db)
    }
}