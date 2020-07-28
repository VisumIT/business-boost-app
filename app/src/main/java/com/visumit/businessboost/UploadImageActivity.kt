package com.visumit.businessboost

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.toBitmap
import com.google.gson.Gson
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.model.ImageUrl
import com.visumit.businessboost.model.Imagem
import com.visumit.businessboost.utils.UserPreferences
import com.visumit.businessboost.utils.bitmapToBase64
import kotlinx.android.synthetic.main.activity_upload_image.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.util.*

class UploadImageActivity : AppCompatActivity() {

    private val CODIGO_RETORNO_FOTO = 2
    private lateinit var imageButton: ImageButton
    private lateinit var imageSalvar: Button
    private lateinit var bitmap : Bitmap
    private lateinit var imageView: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var token: String
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        // Pegando token para realizar requisiçoes
        val preferences = UserPreferences()
        token = preferences.getToken(this).toString()
        id = preferences.getId(this).toString()

        toolbar = findViewById(R.id.toolbar_generic)
        toolbar.title = "Cadastrar Foto"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.statusBarColor = getColor(R.color.colorPrimaryDark)


        imageButton = findViewById(R.id.selecionar_imagem)
//        imageSalvar = findViewById(R.id.btn_salvar_imagem)

        imageView = findViewById(R.id.imageFoto)

        imageButton.setOnClickListener {
            escolherFoto()
        }

//        imageSalvar.setOnClickListener {
//            enviarFoto(token, id)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.nav_save -> {
                enviarFoto(token, id)
                true
            }
            else -> {
                false
            }
        }
    }

    fun escolherFoto(){
        val galeria = Intent()
        galeria.type = "image/*"
        galeria.action = Intent.ACTION_PICK
        startActivityForResult(galeria, CODIGO_RETORNO_FOTO)
    }

    fun enviarFoto(token: String?, id: String?) {
        val random = Random()
        val imagem = Imagem()
//        imagem.fileName = (100000..100000000000).random().toString() + ".jpeg"
        imagem.fileName = "mobile.jpeg"
        imagem.mimetype = "image/jpeg"
        imagem.base64 = bitmapToBase64(imageFoto.drawable.toBitmap())

        val gson = Gson()
        val imagemJson = gson.toJson(imagem)

        doAsync {
            val httpHelper = HttpHelper()
            var res = httpHelper.patch(imagemJson, "representatives/$id/photos", token)

            val gson2 = Gson()
            var toObjectImageUrl = gson2.fromJson(res?.body()?.string(), ImageUrl::class.java)

            if (toObjectImageUrl != null){

                uiThread {
                    toast("Imagem cadastrada com sucesso!")
                    val intent = Intent(this@UploadImageActivity , MainActivity::class.java)
                    startActivity(intent)
                }

            }

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if( resultCode == -1){
            var caminhoFoto = data?.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, caminhoFoto )
                imageView.setImageBitmap(bitmap)
            }catch ( e : IOException ){
                e.printStackTrace()
            }
        }
    }

    fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) +  start
}
