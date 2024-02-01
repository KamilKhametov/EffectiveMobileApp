package com.khametov.effectivemobileapp.presentation.flow

import com.github.terrakok.cicerone.NavigatorHolder
import com.khametov.effectivemobileapp.base.BaseApplication
import com.khametov.effectivemobileapp.base.BaseComponent
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.presentation.flow.di.DaggerFlowComponent
import com.khametov.effectivemobileapp.presentation.flow.di.FlowComponent

object FlowFeature {

    private var component: FlowComponent? = null


    fun getComponent(): FlowComponent =
        component ?: run {
            component = DaggerFlowComponent.factory()
                .create(
                    coreDependencies = FlowCoreDependenciesDelegate(
                        baseComponent = BaseApplication.app.baseComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface FlowCoreDependencies {
    fun provideRouter(): CustomRouter
    fun provideNavigationHolder(): NavigatorHolder
}

internal class FlowCoreDependenciesDelegate(
    private val baseComponent: BaseComponent
): FlowCoreDependencies {

    override fun provideRouter(): CustomRouter {
        return baseComponent.provideRouter()
    }

    override fun provideNavigationHolder(): NavigatorHolder {
        return baseComponent.provideNavigatorHolder()
    }
}