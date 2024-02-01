package com.khametov.effectivemobileapp.core.network.interceptor

import com.khametov.effectivemobileapp.core.data.ClientManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInterceptor @Inject constructor(
    private val clientManager: ClientManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = ""

        var request = chain.request()

        if(request.headers["Authorization"].isNullOrEmpty()) {
            request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }

        return chain.proceed(request)
    }
}