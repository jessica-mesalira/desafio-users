package com.picpay.desafio.android.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PicPayService {

    private val gson: Gson = GsonBuilder().create()
    private val okHttp: OkHttpClient = OkHttpClient.Builder().build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getUserService() : UserService {
        return retrofit.create(UserService::class.java)
    }
}