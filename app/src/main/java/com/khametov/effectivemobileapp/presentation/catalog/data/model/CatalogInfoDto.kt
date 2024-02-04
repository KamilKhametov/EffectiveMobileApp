package com.khametov.effectivemobileapp.presentation.catalog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogInfoDto(
    val title: String,
    val value: String
)