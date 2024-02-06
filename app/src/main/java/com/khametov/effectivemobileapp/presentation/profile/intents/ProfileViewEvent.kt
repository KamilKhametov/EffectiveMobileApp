package com.khametov.effectivemobileapp.presentation.profile.intents

import com.khametov.effectivemobileapp.base.BaseViewEvent

sealed class ProfileViewEvent : BaseViewEvent{

    object NavigateToFavoritesScreen: ProfileViewEvent()
}