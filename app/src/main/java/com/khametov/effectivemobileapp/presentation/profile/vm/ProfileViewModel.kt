package com.khametov.effectivemobileapp.presentation.profile.vm

import androidx.lifecycle.viewModelScope
import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import com.khametov.effectivemobileapp.presentation.profile.domain.repository.ProfileRepository
import com.khametov.effectivemobileapp.presentation.profile.intents.ProfileViewEvent
import com.khametov.effectivemobileapp.presentation.profile.intents.ProfileViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter,
    private val repository: ProfileRepository,
    private val favoritesTracker: FavoritesTracker
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
            is ProfileViewEvent.ClearUserData -> clearUserData()
        }
    }

    init {
        getUserData()
        observeFavorites()
    }

    private fun getUserData() {

        launchCoroutine {

            val userName = repository.getUserFullName()
            val userPhone = repository.getUserPhone()
            val favoritesSize = repository.getFavoritesCount()

            updateState {
                copy(
                    userFullName = userName,
                    userPhone = userPhone,
                    favoritesCount = favoritesSize
                )
            }
        }
    }

    private fun clearUserData() {

        launchCoroutine(isCancelDelay = true) {
            launchCoroutine(isCancelDelay = true) {

                repository.clearUserData()
            }.join()

            router.restartApp()
        }
    }

    private fun observeFavorites() {

        favoritesTracker.observableFavorites().onEach {

            getUserData()
        }.launchIn(viewModelScope)
    }
}