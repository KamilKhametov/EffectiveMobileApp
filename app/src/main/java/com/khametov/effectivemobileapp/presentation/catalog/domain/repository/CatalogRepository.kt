package com.khametov.effectivemobileapp.presentation.catalog.domain.repository

import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity

interface CatalogRepository {

    suspend fun getCatalog(): CatalogEntity
}