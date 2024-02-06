package com.khametov.effectivemobileapp.presentation.catalog.domain.repository

import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

interface CatalogRepository {

    suspend fun getCatalog(): CatalogEntity

    suspend fun saveOrDeleteFavorites(isAdd: Boolean, model: CatalogItemEntity)

    suspend fun getFavorites(): List<CatalogItemEntity>?
}