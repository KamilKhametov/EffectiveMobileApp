package com.khametov.effectivemobileapp.presentation.catalog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(
    val items: List<CatalogItemDto>
)