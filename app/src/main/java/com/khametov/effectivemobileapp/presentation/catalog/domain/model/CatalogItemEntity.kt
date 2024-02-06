package com.khametov.effectivemobileapp.presentation.catalog.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogItemEntity(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: CatalogPriceEntity,
    val feedback: CatalogFeedbackEntity,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<CatalogInfoEntity>,
    val ingredients: String
): Parcelable