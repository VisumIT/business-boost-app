package com.visumit.businessboost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
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
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_company)

        val preferences = PreferenciesUsuario()
        var token = preferences.getToken(this)

        toolbar = findViewById(R.id.toobar)
        toolbar.title = "Empresas"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_pular, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_pular -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                false
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
