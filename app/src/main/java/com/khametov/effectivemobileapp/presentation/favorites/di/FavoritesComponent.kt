package com.khametov.effectivemobileapp.presentation.favorites.di

import com.khametov.effectivemobileapp.core.scopes.FragmentScope
import com.khametov.effectivemobileapp.presentation.favorites.FavoritesCoreDependencies
import com.khametov.effectivemobileapp.presentation.favorites.ui.FavoritesFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [FavoritesModule::class],
    dependencies = [FavoritesCoreDependencies::class]
)
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    @Component.Factory
    interface Builder {

        fun create(
            coreDependencies: FavoritesCoreDependencies
        ): FavoritesComponent
    }
}