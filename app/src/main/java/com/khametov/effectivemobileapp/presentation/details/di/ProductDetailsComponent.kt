package com.khametov.effectivemobileapp.presentation.details.di

import com.khametov.effectivemobileapp.core.scopes.FragmentScope
import com.khametov.effectivemobileapp.presentation.details.ProductDetailsCoreDependencies
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [ProductDetailsModule::class],
    dependencies = [ProductDetailsCoreDependencies::class]
)
interface ProductDetailsComponent {

    fun inject(fragment: ProductDetailsFragment)

    @Component.Factory
    interface Builder {

        fun create(
            coreDependencies: ProductDetailsCoreDependencies
        ): ProductDetailsComponent
    }
}