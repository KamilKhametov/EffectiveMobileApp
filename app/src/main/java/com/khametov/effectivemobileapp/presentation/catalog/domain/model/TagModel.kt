package com.khametov.effectivemobileapp.presentation.catalog.domain.model

data class TagModel(
    val id: String,
    val name: String,
    val type: String,
    val isClicked: Boolean = false
)