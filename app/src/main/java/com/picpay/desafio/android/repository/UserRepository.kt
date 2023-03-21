package com.picpay.desafio.android.repository

import br.com.arch.toolkit.livedata.response.MutableResponseLiveData
import br.com.arch.toolkit.livedata.response.ResponseLiveData
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

class UserRepository(private val userService: UserService) {

    fun getUsers() : ResponseLiveData<List<User>> {
        val userLiveData = MutableResponseLiveData<List<User>>()

        userLiveData.postLoading()
        scope.launch {
            try {
                userLiveData.postData(userService.getUsers())
            } catch (e: Exception){
                userLiveData.postError(e)
            }
        }
        return userLiveData
    }

}