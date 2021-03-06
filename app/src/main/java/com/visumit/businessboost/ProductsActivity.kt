package com.visumit.businessboost

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Product
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

class ProductsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_product)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val preferences = UserPreferences()
        var token = preferences.getToken(this)


        recyclerView = findViewById(R.id.recycler_view_products)
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
