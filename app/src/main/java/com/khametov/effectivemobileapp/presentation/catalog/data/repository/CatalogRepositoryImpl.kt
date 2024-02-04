package com.khametov.effectivemobileapp.presentation.catalog.data.repository

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.core.network.RestApi
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogDto
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.repository.CatalogRepository
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val catalogMapper: BaseMapper<CatalogDto, CatalogEntity>,
    private val restApi: RestApi
): CatalogRepository {

    override suspend fun getCatalog(): CatalogEntity {
        return catalogMapper.map(restApi.getCatalog())
    }
}