package com.utsman.reqres

import android.app.Application
import com.utsman.reqres.di.MainModule
import com.utsman.reqres.event.StateEvent
import kotlinx.coroutines.flow.Flow
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(MainModule().module)
        }
    }
}