package com.utsman.reqres.network

import com.diana.adapter.StateEventAdapterFactory
import com.diana.adapter.StateEventResponse
import com.diana.lib.core.event.StateEventFlow
import com.utsman.reqres.data.UserDetailResponse
import com.utsman.reqres.data.UserResponses
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServicesState {
    @GET(EndPoint.GET_USER)
    suspend fun getList(
        @Query("page") page: Int
    ): StateEventResponse<UserResponses>

    @GET(EndPoint.GET_USER_DETAIL)
    suspend fun getDetail(
        @Path("id") id: String
    ): StateEventResponse<UserDetailResponse>

    companion object {
        private const val BASE_URL = "https://reqres.in"

        private val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        fun build(): WebServicesState {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(StateEventAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(WebServicesState::class.java)
        }
    }

    object EndPoint {
        const val GET_USER = "/api/users"
        const val GET_USER_DETAIL = "/api/users/{id}"
    }
}