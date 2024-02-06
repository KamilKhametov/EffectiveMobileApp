package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.khametov.R
import com.khametov.databinding.ItemProductTitleBlockBinding
import com.khametov.effectivemobileapp.presentation.adapter.BaseDelegateAdapter
import com.khametov.effectivemobileapp.presentation.adapter.DelegateAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

class TitleBlockAdapter:
    BaseDelegateAdapter<ItemProductTitleBlockBinding, ProductDetailsAdapterModel.TitleBlock>() {

    override val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean
    ) -> ItemProductTitleBlockBinding = ItemProductTitleBlockBinding::inflate

    override fun BaseViewHolder<ItemProductTitleBlockBinding>.onBind(entity: ProductDetailsAdapterModel.TitleBlock) {
        with(viewBinding) {

            brandTextView.text = entity.model.brandName
            nameTextView.text = entity.model.title
            availableCountTextView.text = itemView.resources.getQuantityString(
                R.plurals.kt_screen_available_count,
                entity.model.availableCount,
                entity.model.availableCount
            )
        }
    }

    override fun BaseViewHolder<ItemProductTitleBlockBinding>.onBind(
        entity: ProductDetailsAdapterModel.TitleBlock,
        payloads: List<Any>
    ) {
        payloads.forEach { item ->
            if (item is ProductDetailsAdapterModel.TitleBlock) {
                onBind(item)
            }
        }
    }

    override fun isForItem(item: DelegateAdapter.ItemType): Boolean {
        return item is ProductDetailsAdapterModel.TitleBlock
    }

    override fun areItemsTheSame(
        oldItem: ProductDetailsAdapterModel.TitleBlock,
        newItem: ProductDetailsAdapterModel.TitleBlock
    ): Boolean {
        return true
    }

    override fun areContentsTheSame(
        oldItem: ProductDetailsAdapterModel.TitleBlock,
        newItem: ProductDetailsAdapterModel.TitleBlock
    ): Boolean {
        return oldItem.model.title == newItem.model.title &&
                oldItem.model.brandName == newItem.model.brandName &&
                oldItem.model.availableCount == newItem.model.availableCount
    }
}