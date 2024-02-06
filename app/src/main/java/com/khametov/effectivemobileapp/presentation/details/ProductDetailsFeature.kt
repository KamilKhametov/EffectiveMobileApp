package com.khametov.effectivemobileapp.presentation.details

import com.github.terrakok.cicerone.NavigatorHolder
import com.khametov.effectivemobileapp.base.BaseApplication
import com.khametov.effectivemobileapp.base.BaseComponent
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.network.NetworkWrapper
import com.khametov.effectivemobileapp.core.network.RestApi
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import com.khametov.effectivemobileapp.presentation.details.di.DaggerProductDetailsComponent
import com.khametov.effectivemobileapp.presentation.details.di.ProductDetailsComponent

object ProductDetailsFeature {

    private var component: ProductDetailsComponent? = null

    fun getComponent(): ProductDetailsComponent =
        component ?: run {
            component = DaggerProductDetailsComponent.factory()
                .create(
                    coreDependencies = ProductDetailsCoreDependenciesDelegate(
                        baseComponent = BaseApplication.app.baseComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface ProductDetailsCoreDependencies {

    fun provideRouter(): CustomRouter
    fun provideNavigationHolder(): NavigatorHolder
    fun provideClientManager(): ClientManager
    fun provideRestApi(): RestApi
    fun provideFavoritesTracker(): FavoritesTracker
}

internal class ProductDetailsCoreDependenciesDelegate(
    private val baseComponent: BaseComponent
): ProductDetailsCoreDependencies {

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

    override fun provideFavoritesTracker(): FavoritesTracker {
        return baseComponent.provideFavoritesTracker()
    }
}