package com.khametov.effectivemobileapp.core.navigation.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Module
import dagger.Provides
import com.khametov.effectivemobileapp.core.navigation.core.LocalCiceroneHolder
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouterImpl
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone = Cicerone.create(CustomRouterImpl())

    @Provides
    @Singleton
    fun provideAppRouter(): CustomRouter = cicerone.router

    @Provides
    @Singleton
    fun provideAppNavigationHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideCiceroneHolder() = LocalCiceroneHolder()
}
