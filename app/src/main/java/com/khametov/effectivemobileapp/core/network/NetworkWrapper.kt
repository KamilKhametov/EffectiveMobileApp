package com.khametov.effectivemobileapp.core.network

import androidx.annotation.MainThread
import com.khametov.effectivemobileapp.core.network.di.DaggerNetworkComponent
import com.khametov.effectivemobileapp.core.network.di.NetworkComponent
import com.khametov.effectivemobileapp.core.network.interceptor.AppInterceptor


object NetworkWrapper {

    private var component: NetworkComponent? = null

    @MainThread
    fun getApi(): NetworkApi = getComponent().moduleApi()

    @MainThread
    fun initComponent(appInterceptor: AppInterceptor) {
        component = DaggerNetworkComponent.factory()
            .create(appInterceptor)
    }

    internal fun getComponent(): NetworkComponent = requireNotNull(component)
}