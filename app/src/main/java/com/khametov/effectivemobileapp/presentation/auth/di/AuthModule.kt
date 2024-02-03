package com.khametov.effectivemobileapp.presentation.auth.di

import com.khametov.effectivemobileapp.presentation.auth.data.AuthRepositoryImpl
import com.khametov.effectivemobileapp.presentation.auth.domain.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface AuthModule {

    @Binds
    @Reusable
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}