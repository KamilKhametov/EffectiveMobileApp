package com.khametov.effectivemobileapp.presentation.app.events

import com.khametov.effectivemobileapp.base.BaseViewEvent
import com.khametov.effectivemobileapp.core.navigation.navigator.CustomNavigator

sealed class AppViewEvent : BaseViewEvent {
    object InitialTransition: AppViewEvent()
    object RemoveNavigator : AppViewEvent()
    data class SetNavigator(val navigator: CustomNavigator) : AppViewEvent()
}