package com.khametov.effectivemobileapp.presentation.catalog

import com.github.terrakok.cicerone.NavigatorHolder
import com.khametov.effectivemobileapp.base.BaseApplication
import com.khametov.effectivemobileapp.base.BaseComponent
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.network.NetworkWrapper
import com.khametov.effectivemobileapp.core.network.RestApi
import com.khametov.effectivemobileapp.presentation.catalog.di.CatalogComponent
import com.khametov.effectivemobileapp.presentation.catalog.di.DaggerCatalogComponent

object CatalogFeature {

    private var component: CatalogComponent? = null

    fun getComponent(): CatalogComponent =
        component ?: run {
            component = DaggerCatalogComponent.factory()
                .create(
                    coreDependencies = CatalogCoreDependenciesDelegate(
                        baseComponent = BaseApplication.app.baseComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface CatalogCoreDependencies {

    fun provideRouter(): CustomRouter
    fun provideNavigationHolder(): NavigatorHolder
    fun provideClientManager(): ClientManager
    fun provideRestApi(): RestApi
}

internal class CatalogCoreDependenciesDelegate(
    private val baseComponent: BaseComponent
): CatalogCoreDependencies {

    override fun provideRouter(): CustomRouter {
        return baseComponent.provideRouter()
    }

    override fun provideNavigationHolder(): NavigatorHolder {
        return baseComponent.provideNavigatorHolder()
    }

    override fun provideClientManager(): ClientManager {
        return baseComponent.provideClientManager()
    }

    override fun provideRestApi(): RestApi {
        return NetworkWrapper.getApi().provideApiClass()
    }
}