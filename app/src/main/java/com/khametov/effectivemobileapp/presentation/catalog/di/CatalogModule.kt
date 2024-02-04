package com.khametov.effectivemobileapp.presentation.catalog.di

import com.khametov.effectivemobileapp.base.BaseMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.mapper.CatalogFeedbackMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.mapper.CatalogInfoMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.mapper.CatalogItemMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.mapper.CatalogMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.mapper.CatalogPriceMapper
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogFeedbackDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogInfoDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogItemDto
import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogPriceDto
import com.khametov.effectivemobileapp.presentation.catalog.data.repository.CatalogRepositoryImpl
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogFeedbackEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogInfoEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogPriceEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.repository.CatalogRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface CatalogModule {

    @Binds
    @Reusable
    fun bindCatalogRepository(impl: CatalogRepositoryImpl): CatalogRepository

    @Binds
    @Reusable
    fun bindCatalogMapper(impl: CatalogMapper): BaseMapper<CatalogDto, CatalogEntity>

    @Binds
    @Reusable
    fun bindCatalogItemMapper(impl: CatalogItemMapper): BaseMapper<CatalogItemDto, CatalogItemEntity>

    @Binds
    @Reusable
    fun bindCatalogPriceMapper(impl: CatalogPriceMapper): BaseMapper<CatalogPriceDto, CatalogPriceEntity>

    @Binds
    @Reusable
    fun bindCatalogFeedbackMapper(impl: CatalogFeedbackMapper): BaseMapper<CatalogFeedbackDto, CatalogFeedbackEntity>

    @Binds
    @Reusable
    fun bindCatalogInfoMapper(impl: CatalogInfoMapper): BaseMapper<CatalogInfoDto, CatalogInfoEntity>
}