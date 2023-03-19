package com.picpay.desafio.android.repository

import com.picpay.desafio.android.network.UserService

class UserRepository(private val userService: UserService) {

    fun getUsers() {
        userService.getUsers()
    }
}