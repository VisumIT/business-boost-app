package com.visumit.businessboost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.visumit.businessboost.utils.PreferenciesUsuario
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Pegando token para realizar requisiçoes
        val preferences = PreferenciesUsuario()
        var token = preferences.getToken(this)

        toolbar = findViewById(R.id.toobar)
        toolbar.title = "Home"
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


//        var imageViewNav : ImageButton = findViewById(R.id.imageViewNav)
//
//        imageViewNav.setOnClickListener {
//            val uploadFoto = Intent(this, UploadImageActivity::class.java)
//            startActivity(uploadFoto)
//        }
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item : MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                toast("Home Teste")
            }
            R.id.nav_imagem -> {
                var intent = Intent(this, UploadImageActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_produtos -> {
                var intent = Intent(this, ProductsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_companies -> {
                var intent = Intent(this, SelectCompanyActivity::class.java)
                startActivity(intent)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}
