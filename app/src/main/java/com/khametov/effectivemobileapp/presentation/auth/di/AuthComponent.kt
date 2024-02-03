package com.khametov.effectivemobileapp.presentation.auth.di

import com.khametov.effectivemobileapp.core.scopes.FragmentScope
import com.khametov.effectivemobileapp.presentation.auth.AuthCoreDependencies
import com.khametov.effectivemobileapp.presentation.auth.ui.AuthFragment
import dagger.Component

@FragmentScope
@Component(modules = [AuthModule::class], dependencies = [AuthCoreDependencies::class])
interface AuthComponent {

    fun inject(fragment: AuthFragment)

    @Component.Factory
    interface Builder {

        fun create(
            coreDependencies: AuthCoreDependencies
        ): AuthComponent
    }
}