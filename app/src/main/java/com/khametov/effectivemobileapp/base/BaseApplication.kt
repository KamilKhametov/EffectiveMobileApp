package com.khametov.effectivemobileapp.base

import android.app.Application

abstract class BaseApplication : Application() {

    companion object {
        lateinit var app: BaseApplication
    }

    init {
        app = this
    }

    val baseComponent by lazy {
        DaggerBaseComponent.factory().create(this)
    }

}