package com.khametov.effectivemobileapp.core.network.di

import com.khametov.effectivemobileapp.core.scopes.NetworkScope
import dagger.BindsInstance
import dagger.Component
import com.khametov.effectivemobileapp.core.network.NetworkApi
import com.khametov.effectivemobileapp.core.network.NetworkApiImpl
import com.khametov.effectivemobileapp.core.network.interceptor.AppInterceptor

@NetworkScope
@Component(modules = [NetworkModule::class])
internal interface NetworkComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance appInterceptor: AppInterceptor,
        ): NetworkComponent
    }

    fun inject(apiImpl: NetworkApiImpl)

    fun moduleApi(): NetworkApi
}