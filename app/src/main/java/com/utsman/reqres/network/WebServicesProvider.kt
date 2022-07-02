package com.utsman.reqres.network

import org.koin.core.annotation.Single

@Single
class WebServicesProvider {

    fun get(): WebServices {
        return WebServices.build()
    }
}