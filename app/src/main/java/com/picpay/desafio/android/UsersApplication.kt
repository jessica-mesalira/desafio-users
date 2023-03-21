package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.network
import com.picpay.desafio.android.di.repositories
import com.picpay.desafio.android.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@UsersApplication)
            modules(
                listOf(
                    network,
                    repositories,
                    viewModels
                )
            )
        }
    }

}