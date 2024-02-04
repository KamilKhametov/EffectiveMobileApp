package com.khametov.effectivemobileapp.presentation.catalog.data.mapper

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogItemDto
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import javax.inject.Inject

class CatalogMapper @Inject constructor(
    private val itemMapper: BaseMapper<CatalogItemDto, CatalogItemEntity>
): BaseMapper<CatalogDto, CatalogEntity> {

    override fun map(from: CatalogDto): CatalogEntity {
        return CatalogEntity(
            items = from.items.map { catalogItemDto ->
                itemMapper.map(catalogItemDto)
            }
        )
    }
}