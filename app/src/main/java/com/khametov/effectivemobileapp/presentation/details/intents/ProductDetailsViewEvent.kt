package com.khametov.effectivemobileapp.presentation.details.intents

import com.khametov.effectivemobileapp.base.BaseViewEvent
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

sealed class ProductDetailsViewEvent: BaseViewEvent {

    object OnBackPressed: ProductDetailsViewEvent()

    data class SetProductData(
        val model: CatalogItemEntity
    ): ProductDetailsViewEvent()
}