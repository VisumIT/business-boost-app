package com.visumit.businessboost.utils

import android.content.Context

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

    fun getPhotograph(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("photograph", "")

    }

    fun getName(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("name", "")

    }

    fun getToken(context: Context) : String? {

        val preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        return preferences.getString("token", "")

    }
}