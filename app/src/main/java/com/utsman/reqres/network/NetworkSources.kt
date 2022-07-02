package com.utsman.reqres.network

import com.utsman.reqres.data.Mapper
import com.utsman.reqres.data.User
import com.utsman.reqres.utils.FlowState
import com.utsman.reqres.utils.asFlowStateEvent
import com.utsman.reqres.utils.flatMap
import org.koin.core.annotation.Single

@Single
class NetworkSources(private val webServicesProvider: WebServicesProvider) {

    suspend fun getList(page: Int): FlowState<List<User>> {
        return webServicesProvider.get().getList(page).asFlowStateEvent {
            Mapper.mapUserResponses(it)
        }
    }

    /*suspend fun getDetail(id: Int): FlowState<List<User>> {
        return webServicesProvider.get().getList(1).asFlowStateEvent {
            Mapper.mapUserResponses(it)
        }
    }*/
}