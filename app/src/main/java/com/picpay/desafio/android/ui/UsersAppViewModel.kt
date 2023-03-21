package com.picpay.desafio.android.ui

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.repository.UserRepository

class UsersAppViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUsers() = userRepository.getUsers()

}