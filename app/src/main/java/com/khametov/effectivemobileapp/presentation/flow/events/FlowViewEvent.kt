package com.khametov.effectivemobileapp.presentation.flow.events

import com.khametov.effectivemobileapp.base.BaseViewEvent
import com.khametov.effectivemobileapp.core.navigation.core.FlowTab

sealed class FlowViewEvent : BaseViewEvent {
    data class TabSelect(val tab: FlowTab) : FlowViewEvent()
}