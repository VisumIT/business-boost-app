package com.visumit.businessboost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAbrirCadastroRepresentante = findViewById<Button>(R.id.btn_abrir_cadastro_representante)
        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnAbrirCadastroRepresentante.setOnClickListener {
            val abrirCadastroRepresentanteActivity = Intent(this, CadastroRepresentanteActivity::class.java)
            startActivity(abrirCadastroRepresentanteActivity)
        }

    }

}
