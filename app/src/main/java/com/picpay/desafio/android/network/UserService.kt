package com.picpay.desafio.android.network

import com.picpay.desafio.android.domain.User
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUsers(): List<User>
}