package com.visumit.businessboost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Company
import com.visumit.businessboost.utils.PreferenciesUsuario
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

class SelectCompanyActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val companyAdapter = CompanyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_company)

        val preferences = PreferenciesUsuario()
        var token = preferences.getToken(this)

        recyclerView = findViewById(R.id.recyclerViewCompanys)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = companyAdapter

        doAsync {
            var http = HttpHelper()
            var res = http.get("companies", token.toString())

            var listCompany = fromJsonArrayListCompany(res.toString())

            uiThread {
                companyAdapter.updateItems(listCompany)
            }
        }
    }

    private fun fromJsonArrayListCompany(json: String): ArrayList<Company> {
        var companies = ArrayList<Company>()
        var array = JSONArray(json)
        for (i in 0 until array.length()){

            val gson = Gson()
            val item = gson.fromJson(array.getJSONObject(i).toString(), Company::class.java)

            companies.add(item)
        }
        return companies
    }
}
