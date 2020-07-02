package com.visumit.businessboost.http

import okhttp3.*

class HttpHelper {

    fun post(json: String): Response? {

        val URl = "http://192.168.15.13:8080/representatives"

        val headerHttp = MediaType.parse("application/json; charset=utf-8")

        val client = OkHttpClient()

        val body = RequestBody.create(headerHttp, json)

        var request = Request.Builder().url(URl).post(body).build()

        val response = client.newCall(request).execute()
//        println("************* ${response.isSuccessful }")
        return response
    }
}