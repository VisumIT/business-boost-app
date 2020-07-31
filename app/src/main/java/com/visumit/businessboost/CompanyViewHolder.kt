package com.visumit.businessboost

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.visumit.businessboost.model.Company
import org.jetbrains.anko.colorAttr

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
//        println(item.logo.toString())
        if (item.id == 1){
            println("acchou")
            itemView.setBackgroundColor(R.color.colorAccent)
        }

        companyName.text = item.companyName
        companyCity.text = item.city

        itemView.setOnClickListener {
//            Toast.makeText(itemView.context, "Empresa selecionada ${item.companyName}", Toast.LENGTH_SHORT).show()


            val intent = Intent(itemView.context, MainActivity::class.java)
            intent.putExtra("ID_COMPANY", item.id)
            itemView.context.startActivity(intent)
        }


    }

}