package com.visumit.businessboost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Product
import com.visumit.businessboost.utils.PreferenciesUsuario
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

class ProductsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val productAdapter = ProductAdapter()
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        toolbar = findViewById(R.id.toobar)
        toolbar.title = "Lista de Produtos"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val preferences = PreferenciesUsuario()
        var token = preferences.getToken(this)


        recyclerView = findViewById(R.id.recyclerViewProdutos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

        doAsync {

            var http = HttpHelper()
            var res = http.get("company/1/products", token.toString())

            var listProducts = fromJsonArrayListProduct(res.toString())

            uiThread {
                productAdapter.updateItems(listProducts)
            }
        }
    }

    private fun fromJsonArrayListProduct(json: String): ArrayList<Product> {
        var products = ArrayList<Product>()
        var array = JSONArray(json)
        for (i in 0 until array.length()){

            val gson = Gson()
            val item = gson.fromJson(array.getJSONObject(i).toString(), Product::class.java)

            products.add(item)
        }
        return products
    }
}
