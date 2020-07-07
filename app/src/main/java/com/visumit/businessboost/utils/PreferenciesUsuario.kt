package com.visumit.businessboost.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenciesUsuario {


    fun getEmail(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("email", "")

    }

    fun getToken(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("token", "")

    }
}