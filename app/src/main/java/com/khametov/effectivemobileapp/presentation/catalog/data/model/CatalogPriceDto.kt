package com.khametov.effectivemobileapp.presentation.catalog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogPriceDto(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)