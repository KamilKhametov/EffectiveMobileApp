package com.khametov.effectivemobileapp.presentation.favorites.domain.repository

import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

interface FavoritesRepository {

    suspend fun saveOrDeleteFavorites(isAdd: Boolean, model: CatalogItemEntity)

    suspend fun getFavorites(): List<CatalogItemEntity>?
}