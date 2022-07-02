package com.utsman.reqres.repository

import com.utsman.reqres.data.User
import com.utsman.reqres.event.StateEventManager

interface UserRepository {
    val userStateEventManager: StateEventManager<List<User>>

    suspend fun getUsers(page: Int = 1)
}