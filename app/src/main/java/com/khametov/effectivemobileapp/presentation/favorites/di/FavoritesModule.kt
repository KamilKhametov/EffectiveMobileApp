package com.khametov.effectivemobileapp.presentation.favorites.di

import com.khametov.effectivemobileapp.presentation.favorites.data.repository.FavoritesRepositoryImpl
import com.khametov.effectivemobileapp.presentation.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface FavoritesModule {

    @Binds
    @Reusable
    fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository
}