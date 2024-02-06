package com.khametov.effectivemobileapp.presentation.details.domain.repository

import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

interface ProductDetailsRepository {

    suspend fun saveOrDeleteFavorites(isAdd: Boolean, model: CatalogItemEntity)

    suspend fun getFavorites(): List<CatalogItemEntity>?
}