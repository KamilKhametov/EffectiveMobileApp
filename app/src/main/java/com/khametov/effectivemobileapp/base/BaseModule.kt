package com.khametov.effectivemobileapp.base

import dagger.Binds
import dagger.Module
import dagger.Reusable
import com.khametov.effectivemobileapp.common.resourcemanager.ResourceManager
import com.khametov.effectivemobileapp.common.resourcemanager.ResourceManagerImpl
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.core.data.ClientManagerImpl
import com.khametov.effectivemobileapp.core.navigation.di.NavigationModule
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import com.khametov.effectivemobileapp.core.trackers.FavoritesTrackerImpl
import javax.inject.Singleton

@Module(includes = [NavigationModule::class])
internal interface BaseModule {

    @Binds
    @Reusable
    fun bindResourceManager(impl: ResourceManagerImpl): ResourceManager

    @Binds
    @Singleton
    fun bindClientManager(impl: ClientManagerImpl): ClientManager

    @Binds
    @Singleton
    fun bindAuthStateTracker(impl: FavoritesTrackerImpl): FavoritesTracker
}