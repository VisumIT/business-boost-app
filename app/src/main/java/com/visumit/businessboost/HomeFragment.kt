package com.visumit.businessboost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Saldo
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HomeFragment : Fragment() {

    private lateinit var textViewSaldo: TextView
    private val preferences = UserPreferences()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        textViewSaldo = view.findViewById<TextView>(R.id.home_saldo)


        doAsync {

            val http = HttpHelper()

            var res = http.get(
                "representatives/${preferences.getId(view.context)}/saldo",
                preferences.getToken(view.context).toString()
            )

            var gson = Gson()
            var saldo = gson.fromJson<Saldo>(res, Saldo::class.java)

            uiThread {
                println(saldo)
                textViewSaldo.text = "R$ " + String.format("%.2f", saldo.saldo).replace(".", ",")
            }

        }
        return view
    }
}