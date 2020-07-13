package com.visumit.businessboost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.visumit.businessboost.R
import com.visumit.businessboost.model.ClienteResponse
import kotlinx.android.synthetic.main.layout_lista_cliente.view.*
import org.jetbrains.anko.image

class ClientesAdapter(var listaClientes: ArrayList<ClienteResponse>) : RecyclerView.Adapter<ClientesAdapter.ClientViewHolder>() {

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(client : ClienteResponse){
            itemView.textViewNome.text = client.nome
            itemView.textViewCep.text = client.cep
            itemView.textViewCidade.text = client.cidade
            itemView.textViewEmail.text = client.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_lista_cliente, parent, false)

        return ClientViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaClientes.size
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val cliente = listaClientes[position]
        holder.bind(cliente)
    }




}