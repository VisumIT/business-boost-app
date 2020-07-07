package com.visumit.businessboost

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.visumit.businessboost.utils.PreferenciesUsuario

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val preferences = PreferenciesUsuario()

        var token = preferences.getToken(this)
        var email = preferences.getEmail(this)
        println(" ++++++++ " + email)
        println(token)

    }
}
