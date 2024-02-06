package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.khametov.R
import com.khametov.databinding.ItemProductFeedbackAndPriceBlockBinding
import com.khametov.effectivemobileapp.presentation.adapter.BaseDelegateAdapter
import com.khametov.effectivemobileapp.presentation.adapter.DelegateAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

class FeedbackAndPriceBlockAdapter:
    BaseDelegateAdapter<ItemProductFeedbackAndPriceBlockBinding, ProductDetailsAdapterModel.FeedbackAndPriceBlock>() {

    override val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean
    ) -> ItemProductFeedbackAndPriceBlockBinding = ItemProductFeedbackAndPriceBlockBinding::inflate

    override fun BaseViewHolder<ItemProductFeedbackAndPriceBlockBinding>.onBind(entity: ProductDetailsAdapterModel.FeedbackAndPriceBlock) {
        with(viewBinding) {

            ratingBar.rating = entity.model.rating
            ratingCountTextView.text = entity.model.rating.toString()
            reviewsCountTextView.text = itemView.resources.getQuantityString(
                R.plurals.kt_screen_reviews_count,
                entity.model.reviewsCount,
                entity.model.reviewsCount
            )

            priceTextView.text = itemView.context.getString(R.string.price, entity.model.price)
            oldPriceTextView.text = itemView.context.getString(R.string.price, entity.model.oldPrice)
            discountTextView.text = itemView.context.getString(R.string.discount, entity.model.discount)
        }
    }

    override fun BaseViewHolder<ItemProductFeedbackAndPriceBlockBinding>.onBind(
        entity: ProductDetailsAdapterModel.FeedbackAndPriceBlock,
        payloads: List<Any>
    ) {
        payloads.forEach { item ->
            if (item is ProductDetailsAdapterModel.FeedbackAndPriceBlock) {
                onBind(item)
            }
        }
    }

    override fun isForItem(item: DelegateAdapter.ItemType): Boolean {
        return item is ProductDetailsAdapterModel.FeedbackAndPriceBlock
    }

    override fun areItemsTheSame(
        oldItem: ProductDetailsAdapterModel.FeedbackAndPriceBlock,
        newItem: ProductDetailsAdapterModel.FeedbackAndPriceBlock
    ): Boolean {
        return true
    }

    override fun areContentsTheSame(
        oldItem: ProductDetailsAdapterModel.FeedbackAndPriceBlock,
        newItem: ProductDetailsAdapterModel.FeedbackAndPriceBlock
    ): Boolean {
        return oldItem.model.price == newItem.model.price &&
                oldItem.model.oldPrice == newItem.model.oldPrice &&
                oldItem.model.discount == newItem.model.discount &&
                oldItem.model.rating == newItem.model.rating &&
                oldItem.model.reviewsCount == newItem.model.reviewsCount
    }
}