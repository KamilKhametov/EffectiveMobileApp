package com.khametov.effectivemobileapp.presentation.favorites.intents

import com.khametov.effectivemobileapp.base.BaseViewEvent
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

sealed class FavoritesViewEvent: BaseViewEvent {

    object OnBackPressed: FavoritesViewEvent()

    data class AddToFavorites(
        val isAdd: Boolean,
        val model: CatalogItemEntity
    ): FavoritesViewEvent()
}