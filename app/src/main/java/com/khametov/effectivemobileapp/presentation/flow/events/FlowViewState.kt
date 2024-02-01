package com.khametov.effectivemobileapp.presentation.flow.events

import com.khametov.effectivemobileapp.base.BaseViewState
import com.khametov.effectivemobileapp.core.navigation.core.FlowTab

data class FlowViewState(
    val currentTab: FlowTab
): BaseViewState {

    companion object {
        fun provideInitialViewState(): FlowViewState {
            return FlowViewState(
                currentTab = FlowTab.MAIN
            )
        }
    }
}