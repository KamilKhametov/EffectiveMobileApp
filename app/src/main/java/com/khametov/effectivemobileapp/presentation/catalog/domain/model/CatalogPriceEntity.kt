package com.khametov.effectivemobileapp.presentation.catalog.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogPriceEntity(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
): Parcelable