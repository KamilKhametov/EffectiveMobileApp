package com.khametov.effectivemobileapp.presentation.profile.di

import com.khametov.effectivemobileapp.presentation.profile.data.repository.ProfileRepositoryImpl
import com.khametov.effectivemobileapp.presentation.profile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface ProfileModule {

    @Binds
    @Reusable
    fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository
}