package com.visumit.businessboost

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.visumit.businessboost.adapter.ClientesAdapter
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.utils.PreferenciesUsuario
import kotlinx.android.synthetic.main.activity_clientes_representante.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class ClientesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes_representante)

        val preferences = PreferenciesUsuario()
        var token = preferences.getToken(this)

        doAsync {
            val httpHelper = HttpHelper()
            var res = httpHelper.get("customers/representative", token)
            uiThread {
                toast("Clientes")
                val intent = Intent(this@ClientesActivity , HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}