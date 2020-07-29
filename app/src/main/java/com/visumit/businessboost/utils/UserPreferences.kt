package com.visumit.businessboost.utils

import android.content.Context
import android.content.SharedPreferences
import com.visumit.businessboost.http.HttpHelper

class UserPreferences {


    fun getEmail(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("email", "")

    }

    fun getId(context: Context) : Int {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getInt("id", -1)

    }

    fun getIdCompany(context: Context) : Int? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getInt("ID_COMPANY", 1)

    }

    fun getToken(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("token", "")

    }
}