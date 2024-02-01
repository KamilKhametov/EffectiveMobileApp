package com.khametov.effectivemobileapp.presentation.flow.di

import com.khametov.effectivemobileapp.base.BaseContainerFragment
import com.khametov.effectivemobileapp.core.scopes.FragmentScope
import dagger.Component
import com.khametov.effectivemobileapp.presentation.flow.FlowCoreDependencies
import com.khametov.effectivemobileapp.presentation.flow.ui.FlowFragment
import com.khametov.effectivemobileapp.presentation.flow.vm.FlowViewModel

@FragmentScope
@Component(modules = [FlowModule::class], dependencies = [FlowCoreDependencies::class])
interface FlowComponent {

    fun inject(fragment: FlowFragment)
    fun inject(fragment: BaseContainerFragment)

    @Component.Factory
    interface Builder {
        fun create(
            coreDependencies: FlowCoreDependencies
        ): FlowComponent
    }

    fun provideFlowViewModel(): FlowViewModel
}