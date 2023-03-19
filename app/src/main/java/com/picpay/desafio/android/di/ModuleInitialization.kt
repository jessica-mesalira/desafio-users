package com.picpay.desafio.android.di

import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val user = module {
    single { PicPayService().getUserService() }
    single { UserRepository(get()) }
    viewModel { MainActivityViewModel(get()) }
}