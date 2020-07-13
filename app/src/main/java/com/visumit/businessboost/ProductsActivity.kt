package com.visumit.businessboost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.visumit.businessboost.model.Product

class ProductsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        recyclerView = findViewById(R.id.recyclerViewProdutos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

        val products = listOf<Product>(
            Product(1, "Teste", 16.00),
            Product(2, "Teste", 17.00),
            Product(3, "Teste", 18.00),
            Product(4, "Teste", 19.00),
            Product(5, "Teste", 20.00)
        )

        productAdapter.updateItems(products)

    }
}
