package com.khametov.effectivemobileapp.presentation.details.vm

import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

interface ProductDetailsUiBuilder {

    /**
     * Установка контента
     */
    fun setContent(entity: CatalogItemEntity)

    /**
     * Получение контента
     */
    fun getItems(): List<ProductDetailsAdapterModel>
}