package com.utsman.reqres.network

import org.koin.core.annotation.Single
import org.koin.dsl.module

class WebServicesProvider {

    fun get(): WebServices {
        return WebServices.build()
    }

    fun getState(): WebServicesState {
        return WebServicesState.build()
    }

    companion object {
        fun inject() = module {
            single { WebServicesProvider() }
        }
    }
}