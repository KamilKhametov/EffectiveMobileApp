package com.khametov.effectivemobileapp.presentation.catalog.intents

import com.khametov.effectivemobileapp.base.BaseViewState
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity

data class CatalogViewState(
    val catalogEntity: CatalogEntity? = null
): BaseViewState