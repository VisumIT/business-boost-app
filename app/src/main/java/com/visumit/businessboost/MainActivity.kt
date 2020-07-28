package com.visumit.businessboost

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.visumit.businessboost.http.HttpHelper
import com.visumit.businessboost.utils.UserPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle

    private var idCompany : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Home"
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

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


        idCompany = intent.getIntExtra("ID_COMPANY", -1)

        //Toast.makeText(this, "ID_COMPANY: ${idCompany}", Toast.LENGTH_SHORT).show()

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
                toast("Companies")
            }
            R.id.nav_image -> {
                var intent = Intent(this, UploadImageActivity::class.java)
                startActivity(intent)
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
