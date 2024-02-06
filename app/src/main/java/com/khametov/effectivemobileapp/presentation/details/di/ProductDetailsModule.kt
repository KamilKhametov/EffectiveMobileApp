package com.khametov.effectivemobileapp.presentation.details.di

import com.khametov.effectivemobileapp.presentation.details.vm.ProductDetailsUiBuilder
import com.khametov.effectivemobileapp.presentation.details.vm.ProductDetailsUiBuilderImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface ProductDetailsModule {

    @Binds
    @Reusable
    fun bindProductDetailsUiBuilder(impl: ProductDetailsUiBuilderImpl): ProductDetailsUiBuilder
}