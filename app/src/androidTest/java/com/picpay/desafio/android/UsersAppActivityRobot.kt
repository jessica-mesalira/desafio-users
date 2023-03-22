package com.picpay.desafio.android

import android.content.Intent
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.UserService
import com.picpay.desafio.android.ui.UsersAppActivity
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.CoreMatchers.not
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.net.UnknownHostException

fun setup(func: UsersAppActivityRobot.() -> Unit) : UsersAppActivityRobot {
    return UsersAppActivityRobot().apply(func)
}

class UsersAppActivityRobot {
    fun loadUsersSuccess() {
        val userApi = mockk<UserService>(relaxed = true)

        coEvery { userApi.getUsers() } returns listOf(
            User(30,
                "Jessica Pires",
                "jessica_piress",
                "https://randomuser.me/api/portraits/men/30.jpg")
        )

        loadKoinModules(module(override = true) {
            single { userApi }
        })

    }

    fun loadUsersErrorConnection() {
        val userApi = mockk<UserService>(){
            throw UnknownHostException()
        }

        loadKoinModules(module(override = true) {
            single { userApi }
        })
    }

    fun loadUsersGenericError() {
        val userApi = mockk<UserService>(){
            throw IndexOutOfBoundsException()
        }

        loadKoinModules(module(override = true) {
            single { userApi }
        })
    }

    infix fun launch(func: UsersAppActivityRobot.() -> Unit) : UsersAppActivityRobot {
        launchActivity<UsersAppActivity>(Intent(InstrumentationRegistry.getInstrumentation().targetContext,
        UsersAppActivity::class.java))
        return this.apply(func)
    }

    infix fun check(func: UsersAppActivityRobot.() -> Unit) : UsersAppActivityRobot {
        return this.apply(func)
    }

    fun displayedList() {
        onView(withId(R.id.tv_error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.pb_user_list_progress)).check(matches(not(isDisplayed())))

        RecyclerViewMatchers.checkRecyclerViewItem(
            R.id.rv_app_user_list,
            0,
            RecyclerViewMatchers.atPosition(0, isDisplayed())
        )
    }

    fun displayedConnectionWarningMessage() {
        onView(withId(R.id.tv_error_message)).check(matches(withText(
            "Falha na conexão. Verifique seu wi-fi ou dados móveis.")))
        onView(withId(R.id.pb_user_list_progress)).check(matches(isDisplayed()))
    }

    fun displayedGenericWarningMessage() {
        onView(withId(R.id.tv_error_message)).check(matches(withText(
            "Ocorreu um erro. Tente novamente."
        )))
        onView(withId(R.id.pb_user_list_progress)).check(matches(isDisplayed()))
    }
}