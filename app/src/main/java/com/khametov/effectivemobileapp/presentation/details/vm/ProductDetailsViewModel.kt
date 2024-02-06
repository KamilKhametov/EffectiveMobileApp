package com.khametov.effectivemobileapp.presentation.details.vm

import androidx.lifecycle.viewModelScope
import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.trackers.FavoritesTracker
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.details.ProductDetailsFeature
import com.khametov.effectivemobileapp.presentation.details.domain.repository.ProductDetailsRepository
import com.khametov.effectivemobileapp.presentation.details.intents.ProductDetailsViewEvent
import com.khametov.effectivemobileapp.presentation.details.intents.ProductDetailsViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductDetailsViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter,
    private val uiBuilder: ProductDetailsUiBuilder,
    private val favoritesTracker: FavoritesTracker,
    private val repository: ProductDetailsRepository
): BaseViewModel<ProductDetailsViewState, ProductDetailsViewEvent>(
    initialState = ProductDetailsViewState()
) {

    var isFavoriteState = false

    var productId = ""

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("router") router: CustomRouter
        ): ProductDetailsViewModel
    }

    override fun observe(event: ProductDetailsViewEvent) {
        when (event) {
            is ProductDetailsViewEvent.OnBackPressed -> router.back()
            is ProductDetailsViewEvent.SetProductData -> setProductData(event.model)
            is ProductDetailsViewEvent.AddToFavorites -> addToFavorites(event.isAdd, event.model)
        }
    }

    init {
        observeFavorites()
    }

    private fun setProductData(model: CatalogItemEntity) {

        productId = model.id

        launchCoroutine {
            isFavoriteState = repository.getFavorites()?.any { it.id == model.id } == true

            uiBuilder.setContent(model)

            updateState {
                copy(
                    productDataList = uiBuilder.getItems(),
                    isFavorite = isFavoriteState
                )
            }
        }
    }

    private fun addToFavorites(isAdd: Boolean, model: CatalogItemEntity) {

        launchCoroutine {

            repository.saveOrDeleteFavorites(isAdd, model)

            favoritesTracker.setFavorites(true)

            updateState {
                copy(isFavorite = isAdd)
            }
        }
    }

    private fun observeFavorites() {

        favoritesTracker.observableFavorites().onEach {

            isFavoriteState = productId == repository.getFavorites()?.firstOrNull()?.id

            updateState {
                copy(
                    isFavorite = isFavoriteState
                )
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        ProductDetailsFeature.destroyModuleGraph()
        super.onCleared()
    }
}