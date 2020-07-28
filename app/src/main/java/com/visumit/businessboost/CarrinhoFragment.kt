package com.visumit.businessboost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.visumit.businessboost.database.CarrinhoDatabase
import com.visumit.businessboost.database.listarProdutosCarrinho

class CarrinhoFragment : Fragment() {

    private val carrinhoAdapter = CarrinhoAdapter()
    private lateinit var database: CarrinhoDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_carrinho, container, false)
        val activity = activity as Context

        var recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_carrinho)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = carrinhoAdapter

        database = CarrinhoDatabase(activity)

        val carrinhoProdutos = database.listarProdutosCarrinho()

        carrinhoAdapter.updateItems(carrinhoProdutos)

        return view
    }
}
