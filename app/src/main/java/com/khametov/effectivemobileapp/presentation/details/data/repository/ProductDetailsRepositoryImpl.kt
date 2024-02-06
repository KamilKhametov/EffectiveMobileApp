package com.khametov.effectivemobileapp.presentation.details.data.repository

import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.details.domain.repository.ProductDetailsRepository
import javax.inject.Inject

class ProductDetailsRepositoryImpl @Inject constructor(
    private val clientManager: ClientManager
): ProductDetailsRepository {

    override suspend fun saveOrDeleteFavorites(isAdd: Boolean, model: CatalogItemEntity) {
        clientManager.saveOrDeleteFavorites(isAdd, model)
    }

    override suspend fun getFavorites(): List<CatalogItemEntity>? {
        return clientManager.getFavorites()
    }
}