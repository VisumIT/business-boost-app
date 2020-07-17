package com.visumit.businessboost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.visumit.businessboost.model.Company
import com.visumit.businessboost.model.Product

class CompanyAdapter : RecyclerView.Adapter<CompanyViewHolder>() {

    private var items = listOf<Company>()

    fun updateItems(newItems: List<Company>){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_company, parent, false)

        return  CompanyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

}