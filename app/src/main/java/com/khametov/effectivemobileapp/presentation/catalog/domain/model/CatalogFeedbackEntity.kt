package com.khametov.effectivemobileapp.presentation.catalog.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogFeedbackEntity(
    val count: Int,
    val rating: Float
): Parcelable