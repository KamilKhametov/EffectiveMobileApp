package com.khametov.effectivemobileapp.presentation.profile

import com.github.terrakok.cicerone.NavigatorHolder
import com.khametov.effectivemobileapp.base.BaseApplication
import com.khametov.effectivemobileapp.base.BaseComponent
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.network.NetworkWrapper
import com.khametov.effectivemobileapp.core.network.RestApi
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import com.khametov.effectivemobileapp.presentation.profile.di.DaggerProfileComponent
import com.khametov.effectivemobileapp.presentation.profile.di.ProfileComponent

object ProfileFeature {

    private var component: ProfileComponent? = null

    fun getComponent(): ProfileComponent =
        component ?: run {
            component = DaggerProfileComponent.factory()
                .create(
                    coreDependencies = ProfileCoreDependenciesDelegate(
                        baseComponent = BaseApplication.app.baseComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface ProfileCoreDependencies {

    fun provideRouter(): CustomRouter
    fun provideNavigationHolder(): NavigatorHolder
    fun provideClientManager(): ClientManager
    fun provideRestApi(): RestApi
    fun provideTracker(): FavoritesTracker
}

internal class ProfileCoreDependenciesDelegate(
    private val baseComponent: BaseComponent
): ProfileCoreDependencies {

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

    override fun provideTracker(): FavoritesTracker {
        return baseComponent.provideFavoritesTracker()
    }
}