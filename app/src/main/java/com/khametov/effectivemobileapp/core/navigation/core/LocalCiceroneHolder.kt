package com.khametov.effectivemobileapp.core.navigation.core

import com.github.terrakok.cicerone.Cicerone
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouterImpl
import javax.inject.Inject

class LocalCiceroneHolder @Inject constructor() {

    private val containers = HashMap<String, Cicerone<CustomRouterImpl>>()

    fun getCicerone(containerTag: String): Cicerone<CustomRouterImpl> =
        containers.getOrPut(containerTag) {
            Cicerone.create(CustomRouterImpl())
        }
}