package com.khametov.effectivemobileapp.presentation.catalog.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogInfoEntity(
    val title: String,
    val value: String
): Parcelable