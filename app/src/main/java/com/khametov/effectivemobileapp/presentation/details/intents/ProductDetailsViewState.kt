package com.khametov.effectivemobileapp.presentation.details.intents

import com.khametov.effectivemobileapp.base.BaseViewState
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

data class ProductDetailsViewState(
    val productDataList: List<ProductDetailsAdapterModel>? = null,
    val isFavorite: Boolean = false
): BaseViewState