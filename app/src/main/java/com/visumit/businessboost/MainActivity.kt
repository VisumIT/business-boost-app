package com.visumit.businessboost

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var textViewEmail: TextView


    private var idCompany : Int? = null


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         var userPreferences = UserPreferences()

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Home"
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

//        Inflar o header no navigationview, para poder setar os dados do representante
        var hView = navigationView.inflateHeaderView(R.layout.nav_header)

        var txtEmail = hView.findViewById<TextView>(R.id.txt_email_nav)
        var txtNome = hView.findViewById<TextView>(R.id.txt_nome_nav)
        var imgFoto = hView.findViewById<ImageView>(R.id.img_foto_nav)

        txtEmail.text = userPreferences.getEmail(this).toString()
        txtNome.text = userPreferences.getName(this).toString()


//        Picasso.get()
//            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjs5KeYA_AT9Kno6JEi2NQLy3kRAKYkywU4w&usqp=CAU")
//            .resize(50, 50)
//            .into(imgFoto)

        Glide.with(this)
            .load(userPreferences.getPhotograph(this))
            .circleCrop()
            .into(imgFoto)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

//        Vem da tela de cadastro, para selecionar a empresa para qual quer vender
        idCompany = intent.getIntExtra("ID_COMPANY", -1)

        if (idCompany != -1){

            var sharedPref = getSharedPreferences("usuario", Context.MODE_PRIVATE)
            var editor = sharedPref.edit()
            editor.putInt("idCompany", idCompany!!)
            editor.commit()

            val preferences = UserPreferences()
            var idRepresentantePref = preferences.getId(this)
            var tokenPref = preferences.getToken(this)

            doAsync {
                val httpHelper = HttpHelper()
                var res = httpHelper.patch("","companies/${idCompany}/representatives/${idRepresentantePref}", tokenPref.toString())
                if(res?.code() == 200){
                    uiThread {
                        toast("Vinculo com a empresa realizado com sucesso!")
                    }
                }else{
                    uiThread {
                        toast("Solicitaçao enviada com sucesso!")
                    }
                }
            }
        }

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)

        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                toolbar.title = "Home"
                setSupportActionBar(toolbar)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            }
            R.id.nav_products -> {
                toolbar.title = "Produtos"
                setSupportActionBar(toolbar)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductFragment()).commit()
            }
            R.id.nav_companies -> {
                toolbar.title = "Parcerias"
                setSupportActionBar(toolbar)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ParceriasFragment()).commit()
            }
            R.id.nav_image -> {
                var intent = Intent(this, UploadImageActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_carrinho -> {
                toolbar.title = "Carrinho"
                setSupportActionBar(toolbar)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CarrinhoFragment()).commit()
            }
        }

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()



        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
