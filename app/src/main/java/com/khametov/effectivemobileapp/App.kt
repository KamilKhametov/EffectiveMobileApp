package com.khametov.effectivemobileapp

import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.khametov.effectivemobileapp.base.BaseApplication
import com.khametov.effectivemobileapp.base.BaseErrorHandler
import okhttp3.OkHttpClient
import com.khametov.effectivemobileapp.core.network.NetworkWrapper
import com.khametov.effectivemobileapp.presentation.app.di.DaggerAppComponent

class App: BaseApplication() {

    companion object {

        lateinit var app: App
    }

    init {
        app = this
    }

    val appComponent by lazy {
        DaggerAppComponent.factory().create(baseComponent = baseComponent)
    }

    override fun onCreate() {
        super.onCreate()
        initNetwork()
        initErrorHandler()
        initCoilImageLoader()
    }

    private fun initCoilImageLoader() {

        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .bitmapPoolingEnabled(false)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .build()
            }
            .build()

        Coil.setImageLoader(imageLoader)
    }

    private fun initErrorHandler() {

        BaseErrorHandler.init(
            errorHandler = appComponent.provideErrorHandler(),
            errorWrapper = NetworkWrapper.getApi().provideErrorWrapper()
        )
    }

    private fun initNetwork() {
        NetworkWrapper.initComponent(
            appInterceptor = baseComponent.provideRequestInterceptor()
        )
    }
}