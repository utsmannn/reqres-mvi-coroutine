package com.utsman.reqres.network

import com.diana.lib.core.event.StateEventFlow
import com.diana.lib.core.utils.asFlowStateEventMap
import com.diana.lib.core.utils.mapState
import com.utsman.reqres.data.Mapper
import com.utsman.reqres.data.User
import org.koin.dsl.module

class NetworkSources(private val webServicesProvider: WebServicesProvider) {

    suspend fun getList(page: Int): StateEventFlow<List<User>> {
        return webServicesProvider.get().getList(page).asFlowStateEventMap {
            Mapper.mapUserResponses(it)
        }
    }

    suspend fun getListResponse(page: Int): StateEventFlow<List<User>> {
        return webServicesProvider.getState().getList(page).data.mapState {
            Mapper.mapUserResponses(it)
        }
    }

    companion object {
        fun inject() = module {
            single { NetworkSources(get()) }
        }
    }
}