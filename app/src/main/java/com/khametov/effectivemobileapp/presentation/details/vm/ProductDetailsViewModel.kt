package com.khametov.effectivemobileapp.presentation.details.vm

import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.details.ProductDetailsFeature
import com.khametov.effectivemobileapp.presentation.details.intents.ProductDetailsViewEvent
import com.khametov.effectivemobileapp.presentation.details.intents.ProductDetailsViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ProductDetailsViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter,
    private val uiBuilder: ProductDetailsUiBuilder
): BaseViewModel<ProductDetailsViewState, ProductDetailsViewEvent>(
    initialState = ProductDetailsViewState()
) {

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
        }
    }

    private fun setProductData(model: CatalogItemEntity) {

        uiBuilder.setContent(model)

        updateState {
            copy(
                productDataList = uiBuilder.getItems()
            )
        }
    }

    override fun onCleared() {
        ProductDetailsFeature.destroyModuleGraph()
        super.onCleared()
    }
}