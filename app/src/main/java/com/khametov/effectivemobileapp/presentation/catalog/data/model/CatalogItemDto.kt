package com.khametov.effectivemobileapp.presentation.catalog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogItemDto(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: CatalogPriceDto,
    val feedback: CatalogFeedbackDto,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<CatalogInfoDto>,
    val ingredients: String
)