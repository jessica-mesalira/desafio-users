package com.picpay.desafio.android.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.arch.toolkit.livedata.response.MutableResponseLiveData
import br.com.arch.toolkit.livedata.response.ResponseLiveData
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.repository.UserRepository
import retrofit2.Response

class UsersAppViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userLiveData = MutableResponseLiveData<List<User>>()
    val userLiveData : ResponseLiveData<List<User>>
        get() = _userLiveData

    fun getUsers() = userRepository.getUsers(_userLiveData)

    init {
        Log.i("Jessica", "Init view model")
    }

}