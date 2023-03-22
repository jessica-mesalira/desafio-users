package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.arch.toolkit.livedata.response.MutableResponseLiveData
import com.jraska.livedata.test
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.UserService
import com.picpay.desafio.android.repository.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock

class UserRepositoryTest {

    @get:Rule
    val loop = InstantTaskExecutorRule()

    @Test
    fun `when field is not null set success state`() {
        val userApi = mock<UserService>(){
            onBlocking { getUsers() } doReturn listOf(
                User(30,
                    "Jessica Pires",
                    "jessica_piress",
                    "https://randomuser.me/api/portraits/men/30.jpg")
            )
        }

        val userLiveDataTest = MutableResponseLiveData<List<User>>()
        val repository = UserRepository(userApi)

        repository.getUsers(userLiveDataTest)

        val result = userLiveDataTest.test().awaitValue().awaitNextValue().value()

        assertEquals(result.data?.first()?.id, 30)

    }

    @Test
    fun `when generic error appears`() {

        val userApi = mock<UserService>(){
            onBlocking { getUsers() } doThrow IndexOutOfBoundsException()
        }

        val userLiveDataTest = MutableResponseLiveData<List<User>>()
        val repository = UserRepository(userApi)

        repository.getUsers(userLiveDataTest)

        val result = userLiveDataTest.test().awaitValue().awaitNextValue().value()

        assertEquals(result.data, null)
        assertTrue(result.error is IndexOutOfBoundsException)
    }
}