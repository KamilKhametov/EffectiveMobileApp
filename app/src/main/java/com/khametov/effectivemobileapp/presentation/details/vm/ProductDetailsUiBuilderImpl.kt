package com.khametov.effectivemobileapp.presentation.details.vm

import com.khametov.effectivemobileapp.common.extension.uiLazy
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.details.domain.model.DescriptionModel
import com.khametov.effectivemobileapp.presentation.details.domain.model.FeedbackAndPriceModel
import com.khametov.effectivemobileapp.presentation.details.domain.model.SpecificationModel
import com.khametov.effectivemobileapp.presentation.details.domain.model.TitleModel
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel
import javax.inject.Inject

class ProductDetailsUiBuilderImpl @Inject constructor(): ProductDetailsUiBuilder {

    private val content by uiLazy {
        mutableListOf<ProductDetailsAdapterModel>()
    }

    override fun setContent(entity: CatalogItemEntity) {

        content.add(ProductDetailsAdapterModel.SliderBlock(id = entity.id))

        content.add(
            ProductDetailsAdapterModel.TitleBlock(
                TitleModel(
                    brandName = entity.title,
                    title = entity.subtitle,
                    availableCount = entity.available
                )
            )
        )

        content.add(
            ProductDetailsAdapterModel.FeedbackAndPriceBlock(
                FeedbackAndPriceModel(
                    rating = entity.feedback.rating,
                    reviewsCount = entity.feedback.count,
                    price = entity.price.priceWithDiscount,
                    oldPrice = entity.price.price,
                    discount = entity.price.discount
                )
            )
        )

        content.add(
            ProductDetailsAdapterModel.DescriptionBlock(
                DescriptionModel(
                    brandName = entity.title,
                    description = entity.description
                )
            )
        )

        content.add(
            ProductDetailsAdapterModel.SpecificationBlock(
                SpecificationModel(
                    specificationList = entity.info
                )
            )
        )

        content.add(
            ProductDetailsAdapterModel.StructureBlock(
                title = entity.ingredients
            )
        )
    }

    override fun getItems(): List<ProductDetailsAdapterModel> {
        return content.toList()
    }
}