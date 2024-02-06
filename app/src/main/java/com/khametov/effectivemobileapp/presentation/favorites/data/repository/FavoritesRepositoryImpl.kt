package com.khametov.effectivemobileapp.presentation.favorites.data.repository

import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val clientManager: ClientManager
): FavoritesRepository {

    override suspend fun getFavorites(): List<CatalogItemEntity>? {
        return clientManager.getFavorites()
    }

    override suspend fun saveOrDeleteFavorites(isAdd: Boolean, model: CatalogItemEntity) {
        clientManager.saveOrDeleteFavorites(isAdd, model)
    }
}