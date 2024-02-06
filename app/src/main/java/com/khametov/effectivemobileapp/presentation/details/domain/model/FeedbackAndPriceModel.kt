package com.khametov.effectivemobileapp.presentation.details.domain.model

data class FeedbackAndPriceModel(
    val rating: Float,
    val reviewsCount: Int,
    val price: String,
    val oldPrice: String,
    val discount: Int
)