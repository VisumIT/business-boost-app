package com.visumit.businessboost

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Number
import com.visumit.businessboost.model.Representante
import org.jetbrains.anko.*

class CadastroRepresentanteActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_representante)

        val btnGravar = findViewById<Button>(R.id.btn_gravar_representante)
        val editTextNomeCompleto = findViewById<EditText>(R.id.txt_nome_completo)
        val editTextEmail = findViewById<EditText>(R.id.txt_email)
        val editTextCpf = findViewById<EditText>(R.id.txt_cpf)
        val editTextDataNascimento = findViewById<EditText>(R.id.txt_data_nascimento)
        val editTextSenha01 = findViewById<EditText>(R.id.txt_senha_01)
        val editTextSenha02 = findViewById<EditText>(R.id.txt_senha_02)
        val editTextCelular = findViewById<EditText>(R.id.txt_celular)


        btnGravar.setOnClickListener {

            val phones = Number()
//            phones.number = editTextCelular.text.toString()
            val representante = Representante()
//            representante.name = editTextNomeCompleto.text.toString()
//            representante.email = editTextEmail.text.toString()
//            representante.cpf = editTextCpf.text.toString()
//            representante.dateOfBirth = editTextDataNascimento.text.toString()
//            representante.phones.add(phones)
//
//            if(editTextSenha01.text.toString().equals(editTextSenha02.text.toString())){
//                representante.password = editTextSenha01.text.toString()
//            }


            phones.number = "11987168989"
            representante.name = "wesley meneghini"
            representante.email = "wesley@uol.comm"
            representante.cpf = "45302840856"
            representante.dateOfBirth = "1998-03-31"
            representante.phones.add(phones)
            representante.password = "123456789"

            var gson = Gson()
            val representanteJson = gson.toJson(representante)
            println(representanteJson)
            doAsync {

                val http = HttpHelper()
                val response = http.post(representanteJson)
                val respondeObject = gson.fromJson(response, Representante::class.java)

                uiThread {
                    if(respondeObject.id > 0){
                        toast("cadastrado com sucesso")
                        val abrirLogin  = Intent(this@CadastroRepresentanteActivity, MainActivity::class.java)
                        startActivity(abrirLogin)
                    }
                    toast("Erro ao Cadastrar")

                }
            }
        }
    }
}
