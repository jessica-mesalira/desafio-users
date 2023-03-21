package com.picpay.desafio.android.network

import com.picpay.desafio.android.domain.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>
}