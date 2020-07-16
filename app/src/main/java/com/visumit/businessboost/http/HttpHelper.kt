package com.visumit.businessboost.http

import okhttp3.*

class HttpHelper {

    private var IP_REQUEST : String = "10.0.2.2"

    fun post(json: String, endpoint: String, auth: String?): Response? {

        val URl = "http://${IP_REQUEST}:8080/${endpoint}"

        val headerHttp = MediaType.parse("application/json; charset=utf-8")

        val client = OkHttpClient()

        val body = RequestBody.create(headerHttp, json)


        if (auth == null){
            val request = Request.Builder().url(URl).method("POST", body).build()
            val response = client.newCall(request).execute()
            return response
        }else{
            val request = Request.Builder().url(URl).method("POST", body).addHeader("Authorization", auth).build()
            val response = client.newCall(request).execute()
            return response
        }

    }

    fun get( endpoint: String, auth : String): String? {

        val URl = "http://${IP_REQUEST}:8080/${endpoint}"

        val client = OkHttpClient()

//        val body = RequestBody.create(headerHttp, json)

        var request = Request.Builder().url(URl).get().addHeader("Authorization", auth).build()

        val response = client.newCall(request).execute()

        println("************* ${response.isSuccessful }")

        return response.body()?.string()
    }

    fun patch(json: String, endpoint: String, auth: String?): Response? {

        val URl = "http://${IP_REQUEST}:8080/${endpoint}"

        val headerHttp = MediaType.parse("application/json; charset=utf-8")

        val client = OkHttpClient()

        val body = RequestBody.create(headerHttp, json)


        if (auth == null){
            val request = Request.Builder().url(URl).method("PATCH", body).build()
            val response = client.newCall(request).execute()
            return response
        }else{
            val request = Request.Builder().url(URl).method("PATCH", body).addHeader("Authorization", auth).build()
            val response = client.newCall(request).execute()
            return response
        }

    }
}