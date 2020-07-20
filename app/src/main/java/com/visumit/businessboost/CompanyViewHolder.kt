package com.visumit.businessboost

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.visumit.businessboost.model.Company
import com.visumit.businessboost.model.Product
import java.lang.String.format
import java.text.DecimalFormat

class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val companyName = itemView.findViewById<TextView>(R.id.company_name)
    private val companyCity = itemView.findViewById<TextView>(R.id.company_city)
    private val imageCompany : ImageView = itemView.findViewById(R.id.company_img)

    fun bind(item: Company) {
        if (item.logo != "" && item.logo != null){
            Picasso.get().load(item.logo).into(imageCompany)

        }else{
            Picasso.get().load(R.mipmap.boost_icon)
        }
        println(item.logo.toString())

        companyName.text = item.companyName
        companyCity.text = item.city



    }

}