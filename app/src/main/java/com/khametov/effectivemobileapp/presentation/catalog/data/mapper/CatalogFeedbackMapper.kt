package com.khametov.effectivemobileapp.presentation.catalog.data.mapper

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogFeedbackDto
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogFeedbackEntity
import javax.inject.Inject

class CatalogFeedbackMapper @Inject constructor(): BaseMapper<CatalogFeedbackDto, CatalogFeedbackEntity> {

    override fun map(from: CatalogFeedbackDto): CatalogFeedbackEntity {
        return CatalogFeedbackEntity(
            count = from.count,
            rating = from.rating
        )
    }
}