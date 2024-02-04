package com.khametov.effectivemobileapp.presentation.catalog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogFeedbackDto(
    val count: Int,
    val rating: Float
)