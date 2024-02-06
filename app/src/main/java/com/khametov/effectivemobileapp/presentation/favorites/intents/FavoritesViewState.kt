package com.khametov.effectivemobileapp.presentation.favorites.intents

import com.khametov.effectivemobileapp.base.BaseViewState
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

data class FavoritesViewState(
    val favoritesData: List<CatalogItemEntity>? = null
): BaseViewState