package com.khametov.effectivemobileapp.presentation.catalog.vm

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

    private var currentType = 0

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
            is CatalogViewEvent.SortProducts -> {
                updateState {
                    copy(catalogItems = sortProducts(event.type))
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

        currentTag = tag

        val listWithCurrentTag = list.filter { catalogItemEntity ->
            catalogItemEntity.tags.any {

                if (tag == "all") {
                    return allProductsList.checkSort(currentType)
                }

                it == tag
            }
        }

        return listWithCurrentTag.checkSort(currentType)
    }

    private fun List<CatalogItemEntity>.checkSort(type: Int): List<CatalogItemEntity> {
        return when (type) {
            0 -> return sortedByDescending { it.feedback.rating }
            1 -> return sortedByDescending { it.price.priceWithDiscount }
            2 -> return sortedBy { it.price.priceWithDiscount }

            else -> listOf()
        }
    }

    private fun sortProducts(type: Int): List<CatalogItemEntity>? {

        currentType = type

        val list: List<CatalogItemEntity>? = null

        when (type) {
            0 -> {

                return sortByTag(currentTag).sortedByDescending { it.feedback.rating }
            }

            1 -> {

                return sortByTag(currentTag).sortedByDescending { it.price.priceWithDiscount }
            }

            2 -> {

                return sortByTag(currentTag).sortedBy { it.price.priceWithDiscount }
            }
        }

        return list
    }

    private fun observeFavorites() {

        favoritesTracker.observableFavorites().onEach {

            getCatalog()
        }.launchIn(viewModelScope)
    }
}