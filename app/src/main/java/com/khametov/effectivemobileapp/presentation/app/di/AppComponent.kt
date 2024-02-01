package com.khametov.effectivemobileapp.presentation.app.di

import com.khametov.effectivemobileapp.base.BaseComponent
import com.khametov.effectivemobileapp.core.network.error.handler.ErrorHandler
import com.khametov.effectivemobileapp.core.scopes.AppScope
import dagger.Component
import com.khametov.effectivemobileapp.presentation.app.ui.AppActivity

@Component(modules = [AppModule::class], dependencies = [BaseComponent::class])
@AppScope
interface AppComponent : BaseComponent {

    @Component.Factory
    interface Factory {
        fun create(baseComponent: BaseComponent): AppComponent
    }

    fun inject(activity: AppActivity)
    fun provideErrorHandler(): ErrorHandler
}