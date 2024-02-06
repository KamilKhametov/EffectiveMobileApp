package com.khametov.effectivemobileapp.presentation.catalog.intents

import com.khametov.effectivemobileapp.base.BaseViewState
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

data class CatalogViewState(
    val catalogItems: List<CatalogItemEntity>? = null
): BaseViewState