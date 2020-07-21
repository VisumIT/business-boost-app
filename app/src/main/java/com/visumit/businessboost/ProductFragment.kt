package com.visumit.businessboost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Product
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

class ProductFragment : Fragment() {

    private val userPreferences = UserPreferences()
    private val productAdapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_product, container, false)
        val activity = activity as Context
        val token = userPreferences.getToken(activity)

        var recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_products)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = productAdapter

        doAsync {

            var http = HttpHelper()
            var res = http.get("company/1/products", token.toString())

            var listProducts = fromJsonArrayListProduct(res.toString())

            uiThread {
                productAdapter.updateItems(listProducts)
            }
        }

        return view

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