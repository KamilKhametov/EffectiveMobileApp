package com.khametov.effectivemobileapp.core.navigation.core

import com.github.terrakok.cicerone.Cicerone
import com.khametov.R
import com.khametov.effectivemobileapp.base.BaseContainerFragment
import com.khametov.effectivemobileapp.common.extension.uiLazy
import com.khametov.effectivemobileapp.common.extension.withArgs
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouterImpl
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.presentation.flow.FlowFeature

class TabContainerFragment: BaseContainerFragment(layoutResId = R.layout.fragment_tab) {

    private val ciceroneHolder = LocalCiceroneHolder()

    override val transitionType = TransitionType.NONE

    private val containerTag by uiLazy {
        checkNotNull(requireArguments().getString(ARG_CONTAINER_TAG)) {
            "Container tag cannot be null"
        }
    }

    private val position by uiLazy {
        checkNotNull(requireArguments().getInt(ARG_TAB_POSITION)) {
            "Tab position cannot be null"
        }
    }

    override fun setupInjection() {
        FlowFeature.getComponent().inject(this)
    }

    override val cicerone: Cicerone<CustomRouterImpl>
        get() = ciceroneHolder.getCicerone(containerTag = containerTag)

    override val containerResId: Int = R.id.tabContainer

    override fun executeTransactions() {

        when (position) {
            FlowTab.MAIN.position    -> router.replace(Screens.main())
            FlowTab.CATALOG.position    -> router.replace(Screens.catalog())
            FlowTab.BASKET.position    -> router.replace(Screens.basket())
            FlowTab.SALE.position    -> router.replace(Screens.sale())
            FlowTab.PROFILE.position -> router.replace(Screens.profile())
        }
    }

    companion object {

        private const val ARG_CONTAINER_TAG = "ARG_CONTAINER_TAG"
        private const val ARG_TAB_POSITION = "ARG_TAB_POSITION"

        fun newInstance(containerTag: String, position: Int) = TabContainerFragment().withArgs {
            putString(ARG_CONTAINER_TAG, containerTag)
            putInt(ARG_TAB_POSITION, position)
        }
    }
}