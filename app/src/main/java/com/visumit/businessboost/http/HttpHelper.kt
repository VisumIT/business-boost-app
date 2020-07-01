package com.visumit.businessboost.http

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelper {

    fun post(json: String) : String {

        val URl = "http://192.168.15.13:8080/representatives"

        val headerHttp = MediaType.parse("application/json; charset=utf-8")

        val client = OkHttpClient()

        val body = RequestBody.create(headerHttp, json)

        var request = Request.Builder().url(URl).post(body).build()

        val response = client.newCall(request).execute()

        return response.body().toString()
    }
}