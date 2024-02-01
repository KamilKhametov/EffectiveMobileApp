package com.khametov.effectivemobileapp.presentation.flow.vm

import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.core.FlowTab
import com.khametov.effectivemobileapp.presentation.flow.events.FlowViewEvent
import com.khametov.effectivemobileapp.presentation.flow.events.FlowViewState
import com.khametov.effectivemobileapp.presentation.flow.FlowFeature
import javax.inject.Inject

class FlowViewModel @Inject constructor(): BaseViewModel<FlowViewState, FlowViewEvent>(
    initialState = FlowViewState.provideInitialViewState()
) {

    override fun observe(event: FlowViewEvent) {
        when (event) {
            is FlowViewEvent.TabSelect -> applySelectedTab(tab = event.tab)
        }
    }

    private fun applySelectedTab(tab: FlowTab) {

        updateState {
            copy(currentTab = tab)
        }
    }

    override fun onCleared() {
        FlowFeature.destroyModuleGraph()
        super.onCleared()
    }
}