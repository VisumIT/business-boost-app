package com.visumit.businessboost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Company
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

class ParceriasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val companyAdapter = CompanyAdapter()
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_select_company, container, false)

        val preferences = UserPreferences()
        var token = preferences.getToken(view.context)

        recyclerView = view.findViewById(R.id.recyclerViewCompanys)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        recyclerView.adapter = companyAdapter

        doAsync {
            var http = HttpHelper()
            var res = http.get("companies", token.toString())

            var listCompany = fromJsonArrayListCompany(res.toString())

            uiThread {
                companyAdapter.updateItems(listCompany)
            }
        }

        return view
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
