package com.diana.adapter

import com.diana.lib.core.event.StateEventFlow

data class StateEventResponse<T>(
    val data: StateEventFlow<T>
) {
    companion object {
        internal fun <T>create(stateEventFlow: StateEventFlow<T>): StateEventResponse<T> {
            return StateEventResponse(stateEventFlow)
        }
    }
}