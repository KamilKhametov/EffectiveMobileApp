package com.khametov.effectivemobileapp.presentation.catalog.intents

import com.khametov.effectivemobileapp.base.BaseViewEvent
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

sealed class CatalogViewEvent: BaseViewEvent {
    data class NavigateToDetails(
        val model: CatalogItemEntity
    ): CatalogViewEvent()

    data class AddToFavorites(
        val isAdd: Boolean,
        val model: CatalogItemEntity,
        val tag: String
    ): CatalogViewEvent()

    data class SortByTag(
        val tag: String
    ): CatalogViewEvent()

    data class SortProducts(
        val type: Int
    ): CatalogViewEvent()
}