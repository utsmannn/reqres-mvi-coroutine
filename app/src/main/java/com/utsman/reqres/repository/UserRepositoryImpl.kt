package com.utsman.reqres.repository

import com.utsman.reqres.data.User
import com.diana.lib.core.event.StateEventManager
import com.utsman.reqres.network.NetworkSources
import com.diana.lib.core.utils.default

class UserRepositoryImpl(
    private val networkSources: NetworkSources
) : UserRepository {

    private val _userStateEventManager = default<List<User>>()

    override val userStateEventManager: StateEventManager<List<User>>
        get() = _userStateEventManager

    override suspend fun getUsers(page: Int) {
        networkSources.getListResponse(page)
            .collect(_userStateEventManager)
    }

}