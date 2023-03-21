package com.picpay.desafio.android.domain

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("img")
    val img: String
)