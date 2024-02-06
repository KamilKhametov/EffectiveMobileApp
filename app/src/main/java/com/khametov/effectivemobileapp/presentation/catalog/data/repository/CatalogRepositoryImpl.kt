package com.khametov.effectivemobileapp.presentation.catalog.data.repository

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.core.network.RestApi
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogDto
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.repository.CatalogRepository
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val catalogMapper: BaseMapper<CatalogDto, CatalogEntity>,
    private val restApi: RestApi,
    private val clientManager: ClientManager
): CatalogRepository {

    override suspend fun getCatalog(): CatalogEntity {
        return catalogMapper.map(restApi.getCatalog())
    }

    override suspend fun saveOrDeleteFavorites(isAdd: Boolean, model: CatalogItemEntity) {
        clientManager.saveOrDeleteFavorites(isAdd, model)
    }

    override suspend fun getFavorites(): List<CatalogItemEntity>? {
        return clientManager.getFavorites()
    }
}