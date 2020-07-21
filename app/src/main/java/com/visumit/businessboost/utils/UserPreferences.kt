package com.visumit.businessboost.utils

import android.content.Context
import android.content.SharedPreferences

class UserPreferences {


    fun getEmail(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("email", "")

    }

    fun getId(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("id", "")

    }

    fun getToken(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("token", "")

    }
}