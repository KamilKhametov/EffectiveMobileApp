package com.khametov.effectivemobileapp.presentation.favorites.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.favorites.domain.repository.FavoritesRepository
import com.khametov.effectivemobileapp.presentation.favorites.intents.FavoritesViewEvent
import com.khametov.effectivemobileapp.presentation.favorites.intents.FavoritesViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoritesViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter,
    private val repository: FavoritesRepository,
    private val favoritesTracker: FavoritesTracker
): BaseViewModel<FavoritesViewState, FavoritesViewEvent>(
    initialState = FavoritesViewState()
) {

    init {
        getFavorites()
        observeFavorites()
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("router") router: CustomRouter
        ): FavoritesViewModel
    }

    override fun observe(event: FavoritesViewEvent) {
        when (event) {
            is FavoritesViewEvent.OnBackPressed -> router.back()
            is FavoritesViewEvent.AddToFavorites -> addToFavorites(event.isAdd, event.model)
        }
    }

    private fun getFavorites() {

        launchCoroutine {

            val favorites = repository.getFavorites()

            updateState {
                copy(
                    favoritesData = favorites
                )
            }

            checkIsFavorites()
        }
    }

    private fun addToFavorites(isAdd: Boolean, model: CatalogItemEntity) {

        launchCoroutine(isCancelDelay = true) {

            repository.saveOrDeleteFavorites(isAdd, model)

            checkIsFavorites()

            favoritesTracker.setFavorites(true)
        }
    }

    private suspend fun checkIsFavorites() {

        val updatedListWithFavorites = viewStateData.favoritesData?.map { itemEntity ->
            if (repository.getFavorites()?.any { it.id == itemEntity.id } == true) {
                itemEntity.copy(isFavorite = true)
            } else {
                itemEntity.copy(isFavorite = false)
            }
        }

        Log.d("HELLO", repository.getFavorites()?.size.toString())

        updateState {
            copy(
                favoritesData = updatedListWithFavorites?.filter { it.isFavorite }
            )
        }
    }

    private fun observeFavorites() {

        favoritesTracker.observableFavorites().onEach {

            getFavorites()
        }.launchIn(viewModelScope)
    }
}