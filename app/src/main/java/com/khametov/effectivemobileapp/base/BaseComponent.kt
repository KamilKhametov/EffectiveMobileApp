package com.khametov.effectivemobileapp.base

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.khametov.effectivemobileapp.common.resourcemanager.ResourceManager
import com.khametov.effectivemobileapp.core.data.ClientManager
import dagger.BindsInstance
import dagger.Component
import com.khametov.effectivemobileapp.core.navigation.core.LocalCiceroneHolder
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.network.interceptor.AppInterceptor
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import javax.inject.Singleton

@Component(modules = [BaseModule::class])
@Singleton
interface BaseComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): BaseComponent
    }

    fun provideRouter(): CustomRouter
    fun provideNavigatorHolder(): NavigatorHolder
    fun provideCiceroneHolder(): LocalCiceroneHolder
    fun provideResourceManager(): ResourceManager
    fun provideClientManager(): ClientManager
    fun provideRequestInterceptor(): AppInterceptor
    fun provideFavoritesTracker(): FavoritesTracker
}