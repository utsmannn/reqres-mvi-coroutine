package com.utsman.reqres.repository

import com.utsman.reqres.data.User
import com.utsman.reqres.event.StateEventManager
import com.utsman.reqres.network.NetworkSources
import com.utsman.reqres.utils.default
import org.koin.core.annotation.Single

@Single
class UserRepositoryImpl(
    private val networkSources: NetworkSources
) : UserRepository {

    private val _userStateEventManager = default<List<User>>()

    override val userStateEventManager: StateEventManager<List<User>>
        get() = _userStateEventManager

    override suspend fun getUsers(page: Int) {
        networkSources.getList(page)
            .collect(_userStateEventManager)
    }

}