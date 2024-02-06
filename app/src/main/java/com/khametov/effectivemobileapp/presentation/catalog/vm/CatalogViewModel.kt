package com.khametov.effectivemobileapp.presentation.catalog.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.repository.CatalogRepository
import com.khametov.effectivemobileapp.presentation.catalog.intents.CatalogViewEvent
import com.khametov.effectivemobileapp.presentation.catalog.intents.CatalogViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CatalogViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter,
    private val repository: CatalogRepository,
    private val favoritesTracker: FavoritesTracker
): BaseViewModel<CatalogViewState, CatalogViewEvent>(
    initialState = CatalogViewState()
) {

    private var currentTag = "all"

    private var isFirstOpen = true

    val allProductsList = arrayListOf<CatalogItemEntity>()

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("router") router: CustomRouter
        ): CatalogViewModel
    }

    override fun observe(event: CatalogViewEvent) {
        when (event) {
            is CatalogViewEvent.NavigateToDetails -> router.forward(Screens.productDetails(event.model))
            is CatalogViewEvent.AddToFavorites    -> addToFavorites(event.isAdd, event.model, event.tag)
            is CatalogViewEvent.SortByTag -> {
                updateState {
                    copy(catalogItems = sortByTag(event.tag))
                }
            }
        }
    }

    init {
        getCatalog()
        observeFavorites()
    }

    private fun getCatalog() {

        launchCoroutine {

            val entity = repository.getCatalog()

            updateState {
                copy(
                    catalogItems = entity.items
                )
            }

            if (isFirstOpen) {
                allProductsList.addAll(viewStateData.catalogItems ?: listOf())
                isFirstOpen = false
            }

            checkIsFavorites(currentTag)
        }
    }

    private fun addToFavorites(isAdd: Boolean, model: CatalogItemEntity, tag: String) {

        launchCoroutine(isCancelDelay = true) {

            repository.saveOrDeleteFavorites(isAdd, model)

            currentTag = tag

            checkIsFavorites(currentTag)

            favoritesTracker.setFavorites(true)
        }
    }

    private suspend fun checkIsFavorites(tag: String) {

        val updatedListWithFavorites = allProductsList.map { itemEntity ->
            if (repository.getFavorites()?.any { it.id == itemEntity.id } == true) {
                itemEntity.copy(isFavorite = true)
            } else {
                itemEntity.copy(isFavorite = false)
            }
        }

        allProductsList.clear()
        allProductsList.addAll(updatedListWithFavorites)

        updateState {
            copy(
                catalogItems = sortByTag(tag, updatedListWithFavorites)
            )
        }
    }

    private fun sortByTag(tag: String, list: List<CatalogItemEntity> = allProductsList): List<CatalogItemEntity> {

        val listWithCurrentTag = list.filter { catalogItemEntity ->
            catalogItemEntity.tags.any {

                if (tag == "all") {
                    return allProductsList.toList()
                }

                it == tag
            }
        }

        return listWithCurrentTag
    }

    private fun observeFavorites() {

        favoritesTracker.observableFavorites().onEach {

            getCatalog()
        }.launchIn(viewModelScope)
    }
}