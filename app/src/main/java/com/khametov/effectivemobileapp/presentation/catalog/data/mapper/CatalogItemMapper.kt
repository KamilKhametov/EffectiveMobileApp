package com.khametov.effectivemobileapp.presentation.catalog.data.mapper

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogFeedbackDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogInfoDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogItemDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogPriceDto
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogFeedbackEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogInfoEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogPriceEntity
import javax.inject.Inject

class CatalogItemMapper @Inject constructor(
    private val priceMapper: BaseMapper<CatalogPriceDto, CatalogPriceEntity>,
    private val feedbackMapper: BaseMapper<CatalogFeedbackDto, CatalogFeedbackEntity>,
    private val infoMapper: BaseMapper<CatalogInfoDto, CatalogInfoEntity>
): BaseMapper<CatalogItemDto, CatalogItemEntity> {

    override fun map(from: CatalogItemDto): CatalogItemEntity {
        return CatalogItemEntity(
            id = from.id,
            title = from.title,
            subtitle = from.subtitle,
            price = priceMapper.map(from.price),
            feedback = feedbackMapper.map(from.feedback),
            tags = from.tags,
            available = from.available,
            description = from.description,
            info = from.info.map { catalogInfoDto ->
                infoMapper.map(catalogInfoDto)
            },
            ingredients = from.ingredients
        )
    }
}