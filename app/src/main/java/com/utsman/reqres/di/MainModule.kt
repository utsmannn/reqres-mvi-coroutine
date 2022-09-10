package com.utsman.reqres.di

import com.utsman.reqres.network.NetworkSources
import com.utsman.reqres.network.WebServicesProvider
import com.utsman.reqres.repository.UserRepository
import com.utsman.reqres.ui.MainViewModel
import org.koin.dsl.module

class MainModule {
    val module = listOf(
        WebServicesProvider.inject(),
        NetworkSources.inject(),
        UserRepository.inject(),
        MainViewModel.inject()
    )
}