package com.visumit.businessboost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Message
import com.visumit.businessboost.model.Number
import com.visumit.businessboost.model.Representante
import org.jetbrains.anko.*

class CadastroRepresentanteActivity : AppCompatActivity() {

    private lateinit var layoutEmail : TextInputLayout
    private lateinit var layoutCpf : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_representante)

        val btnGravar = findViewById<Button>(R.id.btn_gravar_representante)
        val editTextNomeCompleto = findViewById<EditText>(R.id.txt_nome_completo)
        val editTextEmail = findViewById<TextInputEditText>(R.id.txt_email)
        val editTextCpf = findViewById<TextInputEditText>(R.id.txt_cpf)
        val editTextDataNascimento = findViewById<EditText>(R.id.txt_data_nascimento)
        val editTextSenha01 = findViewById<EditText>(R.id.txt_senha_01)
        val editTextSenha02 = findViewById<EditText>(R.id.txt_senha_02)
        val editTextCelular = findViewById<EditText>(R.id.txt_celular)

        layoutEmail = findViewById(R.id.email_layout)
        layoutCpf = findViewById(R.id.cpf_layout)


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


            phones.number = "11987168981"
            representante.name = "wesley meneghini"
            representante.email = "wesley@uols.kk"
            representante.cpf = "45302840852"
            representante.dateOfBirth = "1998-03-31"
            representante.phones.add(phones)
            representante.password = "123456789"

            var gson = Gson()
            val representanteJson = gson.toJson(representante)
//            println(representanteJson)
            doAsync {

                val http = HttpHelper()
                val response = http.post(representanteJson)

                if (response != null) {
                    if(response.isSuccessful){
                        uiThread {
                            toast("Cadastrado com sucesso")
                            val abrirLogin  = Intent(this@CadastroRepresentanteActivity, MainActivity::class.java)
                            startActivity(abrirLogin)

                        }
                    }
                    val message = gson.fromJson(response.body()?.string(), Message::class.java)
                    uiThread {


                        var error = false

                        if (message.message.equals("E-mail allready in use")){
                            layoutEmail.error = message.message
                            error = true
                        }else{
                            layoutEmail.error = ""
                        }

                        if (message.message.equals("CPF allready in use")){
                            layoutCpf.error = message.message
                            error = true
                        }else{
                            layoutCpf.error = ""
                        }

                    }
//


                }


            }
        }
    }
}
