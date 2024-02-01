package com.khametov.effectivemobileapp.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorWrapper
import dagger.Lazy
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkApiImpl @Inject constructor() : NetworkApi {

    init {
        NetworkWrapper.getComponent().inject(this)
    }

    @Inject lateinit var json: Lazy<Json>
    @Inject lateinit var okhttp3Client: Lazy<OkHttpClient>
    @Inject lateinit var errorWrapper: Lazy<ErrorWrapper>

    override fun provideApiClass(): RestApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp3Client.get())
            .addConverterFactory(json.get().asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RestApi::class.java)
    }

    override fun provideErrorWrapper(): ErrorWrapper {
        return errorWrapper.get()
    }

    private companion object {
        private const val BASE_URL = "http://185.189.100.107:4000/SL/hs/SLapi/"
    }
}