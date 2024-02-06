package com.khametov.effectivemobileapp.presentation.details.ui

import com.khametov.effectivemobileapp.presentation.adapter.DelegateAdapter
import com.khametov.effectivemobileapp.presentation.details.domain.model.DescriptionModel
import com.khametov.effectivemobileapp.presentation.details.domain.model.FeedbackAndPriceModel
import com.khametov.effectivemobileapp.presentation.details.domain.model.SpecificationModel
import com.khametov.effectivemobileapp.presentation.details.domain.model.TitleModel

sealed class ProductDetailsAdapterModel: DelegateAdapter.ItemType {
    data class SliderBlock(val id: String): ProductDetailsAdapterModel()
    data class TitleBlock(val model: TitleModel): ProductDetailsAdapterModel()
    data class FeedbackAndPriceBlock(val model: FeedbackAndPriceModel): ProductDetailsAdapterModel()
    data class DescriptionBlock(val model: DescriptionModel): ProductDetailsAdapterModel()
    data class SpecificationBlock(val model: SpecificationModel): ProductDetailsAdapterModel()
    data class StructureBlock(val title: String): ProductDetailsAdapterModel()
}