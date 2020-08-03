package com.visumit.businessboost

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Email
import com.visumit.businessboost.model.Login
import com.visumit.businessboost.model.RepresentanteResponse
import com.visumit.businessboost.model.ResponseWhoisRepresentantive
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlin.reflect.typeOf

class LoginActivity : AppCompatActivity(){

    private lateinit var btnLogin : Button
    private lateinit var btnEsqueceuSenha : Button
    private lateinit var btnAbrirCadastroRepresentante : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.statusBarColor = getColor(R.color.colorPrimary)

        val email = findViewById<EditText>(R.id.txt_login_email)
        val password = findViewById<EditText>(R.id.txt_login_password)

        btnLogin = findViewById(R.id.btn_login)
        btnEsqueceuSenha = findViewById(R.id.btn_esqueceu_senha)
        btnAbrirCadastroRepresentante = findViewById(R.id.btn_abrir_cadastro_representante)

        btnLogin.setOnClickListener {

            var login = Login()
            login.email = email.text.toString()
            login.password = password.text.toString()

//            login.email = "agatha@gmail.com"
//            login.password = "123456789"

            if(login.email != "" && login.password != ""){

                var gson = Gson()
                var loginJson = gson.toJson(login)

                doAsync {

                    var http = HttpHelper()
                    var res = http.post(loginJson, "login", null)

                    if (res != null) {
                        if (res.code() == 200) {

                            println(res)
                            println(res.headers().toString())

                            val token = res.header("Authorization")

                            var sharedPref = getSharedPreferences("usuario", Context.MODE_PRIVATE)
                            var editor = sharedPref.edit()

                            editor.putString("token", token.toString())
                            editor.putString("email", login.email)
                            editor.putString("password", login.password)



                            val email = Email(email = login.email)
                            var res = http.post(gson.toJson(email) ,"representatives/whois", "$token")

                            val responseBody = res?.body()
                            var dados = responseBody?.string()
                            var toOject = gson.fromJson(dados, ResponseWhoisRepresentantive::class.java)

                            editor.putInt("id", toOject.id.toString().toInt())
                            editor.putString("photograph", toOject.photograph)
                            editor.putString("name", toOject.name.toString())
                            editor.putInt("ID_COMPANY", 2)
                            editor.commit()

                            uiThread {
                                val abrirApp = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(abrirApp)
                            }
                        }
                    }else{
                        uiThread {
                            toast("dados invalidos")
                        }
                    }
                }
            }else{
                toast("Digite seu email e senha!")
            }
        }

        btnEsqueceuSenha.setOnClickListener {
            longToast("Em construçao")
        }

        btnAbrirCadastroRepresentante.setOnClickListener {
            val abrirCadastroRepresentanteActivity = Intent(this, CadastroRepresentanteActivity::class.java)
            startActivity(abrirCadastroRepresentanteActivity)
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }

}
