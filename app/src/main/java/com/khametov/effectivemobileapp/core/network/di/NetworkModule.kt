package com.khametov.effectivemobileapp.core.network.di

import com.khametov.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorWrapper
import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorWrapperImpl
import com.khametov.effectivemobileapp.core.scopes.NetworkScope
import com.khametov.effectivemobileapp.core.network.NetworkApi
import com.khametov.effectivemobileapp.core.network.NetworkApiImpl
import com.khametov.effectivemobileapp.core.network.interceptor.AppInterceptor

@Module
internal abstract class NetworkModule {

    @Binds
    @NetworkScope
    abstract fun bindModuleApi(impl: NetworkApiImpl): NetworkApi

    @Binds
    @Reusable
    abstract fun bindErrorWrapper(impl: ErrorWrapperImpl): ErrorWrapper

    companion object {

        @Provides
        @Reusable
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @Provides
        @Reusable
        fun provideOkHttp3(
            loggingInterceptor: HttpLoggingInterceptor,
            appInterceptor: AppInterceptor,
        ): OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(appInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
        }.build()

        @Provides
        @Reusable
        fun provideJson(): Json {
            return Json(Json.Default) {
                isLenient = true
                ignoreUnknownKeys = true
            }
        }
    }
}