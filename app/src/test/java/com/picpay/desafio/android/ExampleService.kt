package com.picpay.desafio.android

import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.ApplicationService

class ExampleService(
    private val service: ApplicationService
) {

    fun example(): List<User> {
        val users = service.getUserService()

        return users.body() ?: emptyList()
    }
}