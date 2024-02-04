package com.khametov.effectivemobileapp.presentation.catalog.di

import com.khametov.effectivemobileapp.core.scopes.FragmentScope
import com.khametov.effectivemobileapp.presentation.catalog.CatalogCoreDependencies
import com.khametov.effectivemobileapp.presentation.catalog.ui.CatalogFragment
import dagger.Component

@FragmentScope
@Component(modules = [CatalogModule::class], dependencies = [CatalogCoreDependencies::class])
interface CatalogComponent {

    fun inject(fragment: CatalogFragment)

    @Component.Factory
    interface Builder {

        fun create(
            coreDependencies: CatalogCoreDependencies
        ): CatalogComponent
    }
}