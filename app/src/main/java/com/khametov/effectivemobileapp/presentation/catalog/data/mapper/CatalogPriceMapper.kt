package com.khametov.effectivemobileapp.presentation.catalog.data.mapper

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogPriceDto
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogPriceEntity
import javax.inject.Inject

class CatalogPriceMapper @Inject constructor(): BaseMapper<CatalogPriceDto, CatalogPriceEntity> {

    override fun map(from: CatalogPriceDto): CatalogPriceEntity {
        return CatalogPriceEntity(
            price = from.price,
            discount = from.discount,
            priceWithDiscount = from.priceWithDiscount,
            unit = from.unit
        )
    }
}