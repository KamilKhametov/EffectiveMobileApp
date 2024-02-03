package com.khametov.effectivemobileapp.presentation.app.di

import androidx.lifecycle.ViewModel
import com.khametov.effectivemobileapp.core.network.error.callback.ErrorCallback
import com.khametov.effectivemobileapp.core.network.error.callback.ErrorCallbackHandler
import com.khametov.effectivemobileapp.core.network.error.handler.ErrorHandler
import com.khametov.effectivemobileapp.core.network.error.handler.ErrorHandlerImpl
import com.khametov.effectivemobileapp.core.scopes.AppScope
import com.khametov.effectivemobileapp.presentation.app.data.AppRepositoryImpl
import com.khametov.effectivemobileapp.presentation.app.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.svet.presentation.app.di.ViewModelModule
import com.khametov.effectivemobileapp.presentation.app.vm.AppViewModel
import dagger.Reusable

@Module
interface AppModule {

    @Binds
    @AppScope
    fun bindErrorCallback(impl: ErrorCallbackHandler): ErrorCallback

    @Binds
    @AppScope
    fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler

    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(AppViewModel::class)
    fun bindAppViewModel(viewModel: AppViewModel): ViewModel

    @Binds
    @Reusable
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}