package com.utsman.reqres.repository

import com.utsman.reqres.data.User
import com.diana.lib.core.event.StateEventManager
import org.koin.dsl.module

interface UserRepository {
    val userStateEventManager: StateEventManager<List<User>>

    suspend fun getUsers(page: Int = 1)

    companion object {
        fun inject() = module {
            single<UserRepository> { UserRepositoryImpl(get()) }
        }
    }
}