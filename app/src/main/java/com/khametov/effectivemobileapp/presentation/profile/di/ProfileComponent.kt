package com.khametov.effectivemobileapp.presentation.profile.di

import com.khametov.effectivemobileapp.core.scopes.FragmentScope
import com.khametov.effectivemobileapp.presentation.profile.ProfileCoreDependencies
import com.khametov.effectivemobileapp.presentation.profile.ui.ProfileFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [ProfileModule::class],
    dependencies = [ProfileCoreDependencies::class]
)
interface ProfileComponent {

    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Builder {

        fun create(
            coreDependencies: ProfileCoreDependencies
        ): ProfileComponent
    }
}