package com.khametov.effectivemobileapp.presentation.catalog.domain.model

data class CatalogPriceEntity(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)