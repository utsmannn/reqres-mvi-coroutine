package com.diana.lib.core.utils

import com.diana.lib.core.event.MutableStateEventManager
import com.diana.lib.core.event.StateEvent
import com.diana.lib.core.event.StateEventFlow
import com.diana.lib.core.exception.StateApiException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

fun <T> default() = MutableStateEventManager<T>()

inline fun <T, U> Response<T>.asFlowStateEventMap(
    crossinline mapper: (T) -> U
): StateEventFlow<U> {
    return flow {
        emit(StateEvent.Loading())
        delay(2000)
        val emitData = try {
            val body = body()
            if (isSuccessful && body != null) {
                val dataMapper = mapper.invoke(body)
                StateEvent.Success(dataMapper)
            } else {
                val message = errorBody()?.string().orEmpty()
                val exception = StateApiException(message, code())
                StateEvent.Failure(exception)
            }
        } catch (e: Throwable) {
            StateEvent.Failure(e)
        }

        emit(emitData)
    }
}


fun <T> Response<T>.asFlowStateEvent(): StateEventFlow<T> {
    return flow {
        emit(StateEvent.Loading())
        delay(2000)
        val emitData = try {
            val body = body()
            if (isSuccessful && body != null) {
                StateEvent.Success<T>(body)
            } else {
                val message = errorBody()?.string().orEmpty()
                val exception = StateApiException(message, code())
                StateEvent.Failure<T>(exception)
            }
        } catch (e: Throwable) {
            StateEvent.Failure<T>(e)
        }

        emit(emitData)
    }
}

fun <T, U>StateEvent<T>.mapState(mapper: (T) -> U): StateEvent<U> {
    return when (this) {
        is StateEvent.Idle -> StateEvent.Idle()
        is StateEvent.Loading -> StateEvent.Loading()
        is StateEvent.Failure -> StateEvent.Failure(this.exception)
        is StateEvent.Success -> StateEvent.Success(mapper.invoke(this.data))
    }
}

fun <T, U>StateEventFlow<T>.mapState(mapper: (T) -> U): StateEventFlow<U> {
    return this.map { it.mapState(mapper) }
}