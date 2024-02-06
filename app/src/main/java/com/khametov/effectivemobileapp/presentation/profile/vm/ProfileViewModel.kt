package com.khametov.effectivemobileapp.presentation.profile.vm

import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.presentation.profile.intents.ProfileViewEvent
import com.khametov.effectivemobileapp.presentation.profile.intents.ProfileViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ProfileViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter
): BaseViewModel<ProfileViewState, ProfileViewEvent>(
    initialState = ProfileViewState()
) {

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("router") router: CustomRouter
        ): ProfileViewModel
    }

    override fun observe(event: ProfileViewEvent) {
        when (event) {
            is ProfileViewEvent.NavigateToFavoritesScreen -> router.forward(Screens.favorites())
        }
    }
}