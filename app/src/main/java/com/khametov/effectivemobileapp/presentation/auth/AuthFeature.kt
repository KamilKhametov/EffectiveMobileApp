package com.khametov.effectivemobileapp.presentation.auth

import com.github.terrakok.cicerone.NavigatorHolder
import com.khametov.effectivemobileapp.base.BaseApplication
import com.khametov.effectivemobileapp.base.BaseComponent
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.presentation.auth.di.AuthComponent
import com.khametov.effectivemobileapp.presentation.auth.di.DaggerAuthComponent

object AuthFeature {

    private var component: AuthComponent? = null

    fun getComponent(): AuthComponent =
        component ?: run {
            component = DaggerAuthComponent.factory()
                .create(
                    coreDependencies = AuthCoreDependenciesDelegate(
                        baseComponent = BaseApplication.app.baseComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface AuthCoreDependencies {

    fun provideRouter(): CustomRouter
    fun provideNavigationHolder(): NavigatorHolder
    fun provideClientManager(): ClientManager
}

internal class AuthCoreDependenciesDelegate(
    private val baseComponent: BaseComponent
): AuthCoreDependencies {

    override fun provideRouter(): CustomRouter {
        return baseComponent.provideRouter()
    }

    override fun provideNavigationHolder(): NavigatorHolder {
        return baseComponent.provideNavigatorHolder()
    }

    override fun provideClientManager(): ClientManager {
        return baseComponent.provideClientManager()
    }
}