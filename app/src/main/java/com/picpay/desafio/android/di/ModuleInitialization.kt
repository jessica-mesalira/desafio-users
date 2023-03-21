package com.picpay.desafio.android.di

import com.picpay.desafio.android.network.ApplicationService
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { MainActivityViewModel(get()) }
}

val repositories = module {
    single { UserRepository(get()) }
}

val network = module {
    single { ApplicationService().getUserService() }
}