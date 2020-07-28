package com.visumit.businessboost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.Message
import com.visumit.businessboost.model.Number
import com.visumit.businessboost.model.Representante
import com.visumit.businessboost.utils.FormValidationUtil
import com.visumit.businessboost.utils.Mask
import org.jetbrains.anko.*

class CadastroRepresentanteActivity : AppCompatActivity() {

    private lateinit var layoutEmail : TextInputLayout
    private lateinit var layoutCpf : TextInputLayout
    private lateinit var layoutSenha01 : TextInputLayout
    private lateinit var layoutSenha02 : TextInputLayout
    private lateinit var layoutCelular : TextInputLayout
    private lateinit var btnGravar : Button
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_representante)

        toolbar = findViewById(R.id.toolbar_cadastro_representante)
        toolbar.title = "Cadastro de Representante"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.statusBarColor = getColor(R.color.colorPrimaryDark)


        // edit texts
        val editTextNomeCompleto = findViewById<TextInputEditText>(R.id.txt_nome_completo)
        val editTextEmail = findViewById<TextInputEditText>(R.id.txt_email)
        val editTextCpf = findViewById<TextInputEditText>(R.id.txt_cpf)
        val editTextDataNascimento = findViewById<TextInputEditText>(R.id.txt_data_nascimento)
        val editTextSenha01 = findViewById<TextInputEditText>(R.id.txt_senha_01)
        val editTextSenha02 = findViewById<TextInputEditText>(R.id.txt_senha_02)
        val editTextCelular = findViewById<TextInputEditText>(R.id.txt_celular)

        // layouts
        layoutEmail = findViewById(R.id.email_layout)
        layoutCpf = findViewById(R.id.cpf_layout)
        layoutSenha01 = findViewById(R.id.senha_01_layout)
        layoutSenha02 = findViewById(R.id.senha_02_layout)
        layoutCelular = findViewById(R.id.celular_layout)

        editTextCpf.addTextChangedListener(Mask.mask("###.###.###-##", editTextCpf))
        editTextCelular.addTextChangedListener(Mask.mask("(##) # ####-####", editTextCelular))
        editTextDataNascimento.addTextChangedListener(Mask.mask("##/##/####", editTextDataNascimento))
        btnGravar = findViewById(R.id.btn_gravar_representante)

        btnGravar.setOnClickListener {

            var dataNascimento = editTextDataNascimento.text.toString().split("/")

            val phones = Number()
            phones.number = editTextCelular.text.toString()

            val representante = Representante()
            representante.name = editTextNomeCompleto.text.toString()
            representante.email = editTextEmail.text.toString()
            representante.cpf = editTextCpf.text.toString()
            representante.dateOfBirth = "${dataNascimento[2]}-${dataNascimento[1]}-${dataNascimento[0]}"
            representante.phones.add(phones)

            var error = false

            if(!FormValidationUtil.myVadidateCelular(editTextCelular.text.toString())){
                layoutCelular.error = "Numero invalido"
                error = true
            }else{
                layoutCelular.isErrorEnabled = false
            }

            if(!FormValidationUtil.myValidateCPF(editTextCpf.text.toString())) {
                layoutCpf.error = "CPF invalido"
                error = true
            }else{
                layoutCpf.isErrorEnabled = false
            }
            if(editTextSenha01.text.toString() != editTextSenha02.text.toString()) {
                layoutSenha01.error = "Senhas nao corresponde"
                layoutSenha02.error = "Senhas nao corresponde"
                error = true
            }else{
                layoutSenha01.isErrorEnabled = false
                layoutSenha02.isErrorEnabled = false
            }
            if(!error){
                representante.password = editTextSenha01.text.toString()

                var gson = Gson()
                val representanteJson = gson.toJson(representante)

                doAsync {

                    val http = HttpHelper()
                    val response = http.post(representanteJson, "representatives", null)

                    if (response != null) {
                        if(response.isSuccessful){

                            uiThread {
                                toast("Cadastrado realizado com sucesso!")
                                val selecionarEmpresa  = Intent(this@CadastroRepresentanteActivity, SelectCompanyActivity::class.java)
                                startActivity(selecionarEmpresa)
//                                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left)

                            }
                        }
                        val message = gson.fromJson(response.body()?.string(), Message::class.java)
                        uiThread {
                                println(message)
                            if (message.message == "E-mail allready in use"){
                                layoutEmail.error = "E-mail ja esta em uso"
                                error = true
                            }else{
                                layoutEmail.isErrorEnabled = false
                            }

                            if (message.message == "CPF allready in use"){
                                layoutCpf.error = "CPF ja cadastrado"
                                error = true
                            }else{
                                layoutCpf.isErrorEnabled = false
                            }
                        }
                    }
                }
            }
        }
    }
}
