package com.visumit.businessboost

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.visumit.businessboost.database.CarrinhoDatabase
import com.visumit.businessboost.database.deletaTodosItensCarrinho
import com.visumit.businessboost.database.listarProdutosCarrinho
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Items
import com.visumit.businessboost.model.Order
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread

class CarrinhoFragment : Fragment() {

    private lateinit var buttonLimpar: Button
    private lateinit var buttonEnviar: Button
    private val carrinhoAdapter = CarrinhoAdapter()
    private lateinit var database: CarrinhoDatabase
    private val preferences = UserPreferences()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_carrinho, container, false)
        val activity = activity as Context



        buttonLimpar = view.findViewById(R.id.limpar_carrinho)
        buttonEnviar = view.findViewById(R.id.enviar_pedido)

        var recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_carrinho)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = carrinhoAdapter

        database = CarrinhoDatabase(activity)

        val carrinhoProdutos = database.listarProdutosCarrinho()
        var listProducts = database.listarProdutosCarrinho()

        carrinhoAdapter.updateItems(carrinhoProdutos)

        buttonLimpar.setOnClickListener {
            limparCarrinho()
        }

        buttonEnviar.setOnClickListener {
            var listProducts = database.listarProdutosCarrinho()

            var listItems = mutableListOf<Items>()
            for (item in listProducts){
                var items = Items(
                    productId = item.idProduct.toInt(),
                    quantity = item.quantidade.toInt()
                )
                listItems.add(items)
            }
            println(preferences.getId(view.context))
            val orders = Order(
                representativeId = preferences.getId(view.context),
                clientId = 1,
                dicountId = "0",
                items = listItems
            )

            val gson = Gson()
            var orderGson = gson.toJson(orders)
            println()
            println(orderGson)
            println(preferences.getToken(view.context))
            val httpHelper = HttpHelper()
            var endpoint = "company/"+preferences.getIdCompany(view.context)+"/representantive/"+preferences.getId(view.context)+"/client/1/orders"
            println("******************* $endpoint")
            doAsync {
                val res = httpHelper.post( orderGson , endpoint, preferences.getToken(view.context))
                if(res?.code() == 201){
                    uiThread {
                        toast("Pedido enviado com sucesso!")
                        limparCarrinho()
                    }
                }
            }


        }








        return view
    }

    fun limparCarrinho(){
        database.deletaTodosItensCarrinho()
        carrinhoAdapter.updateItems(database.listarProdutosCarrinho())
    }


}
