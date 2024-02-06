package com.khametov.effectivemobileapp.presentation.catalog.intents

import com.khametov.effectivemobileapp.base.BaseViewEvent
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

sealed class CatalogViewEvent: BaseViewEvent {
    data class NavigateToDetails(
        val model: CatalogItemEntity
    ): CatalogViewEvent()
}