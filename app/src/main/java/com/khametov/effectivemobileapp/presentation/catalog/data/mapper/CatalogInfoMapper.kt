package com.khametov.effectivemobileapp.presentation.catalog.data.mapper

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogInfoDto
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogInfoEntity
import javax.inject.Inject

class CatalogInfoMapper @Inject constructor(): BaseMapper<CatalogInfoDto, CatalogInfoEntity> {

    override fun map(from: CatalogInfoDto): CatalogInfoEntity {
        return CatalogInfoEntity(
            title = from.title,
            value = from.value
        )
    }
}