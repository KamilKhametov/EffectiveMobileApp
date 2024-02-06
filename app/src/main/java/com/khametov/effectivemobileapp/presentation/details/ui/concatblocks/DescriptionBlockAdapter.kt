package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.khametov.R
import com.khametov.databinding.ItemProductDescriptionBlockBinding
import com.khametov.effectivemobileapp.presentation.adapter.BaseDelegateAdapter
import com.khametov.effectivemobileapp.presentation.adapter.DelegateAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

class DescriptionBlockAdapter:
    BaseDelegateAdapter<ItemProductDescriptionBlockBinding, ProductDetailsAdapterModel.DescriptionBlock>() {

    private var isHide = true

    override val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean
    ) -> ItemProductDescriptionBlockBinding = ItemProductDescriptionBlockBinding::inflate

    override fun BaseViewHolder<ItemProductDescriptionBlockBinding>.onBind(entity: ProductDetailsAdapterModel.DescriptionBlock) {
        with(viewBinding) {

            brandTextView.text = entity.model.brandName

            descriptionTextView.text = entity.model.description

            showMoreTextView.setOnClickListener {
                if (isHide) {
                    descriptionTextView.maxLines = Int.MAX_VALUE
                    showMoreTextView.text =
                        itemView.context.getString(R.string.kt_screen_hide_title)

                    isHide = false
                } else {
                    descriptionTextView.maxLines = 3
                    showMoreTextView.text =
                        itemView.context.getString(R.string.kt_screen_show_more_title)

                    isHide = true
                }
            }
        }
    }

    override fun BaseViewHolder<ItemProductDescriptionBlockBinding>.onBind(
        entity: ProductDetailsAdapterModel.DescriptionBlock,
        payloads: List<Any>
    ) {
        payloads.forEach { item ->
            if (item is ProductDetailsAdapterModel.DescriptionBlock) {
                onBind(item)
            }
        }
    }

    override fun isForItem(item: DelegateAdapter.ItemType): Boolean {
        return item is ProductDetailsAdapterModel.DescriptionBlock
    }

    override fun areItemsTheSame(
        oldItem: ProductDetailsAdapterModel.DescriptionBlock,
        newItem: ProductDetailsAdapterModel.DescriptionBlock
    ): Boolean {
        return true
    }

    override fun areContentsTheSame(
        oldItem: ProductDetailsAdapterModel.DescriptionBlock,
        newItem: ProductDetailsAdapterModel.DescriptionBlock
    ): Boolean {
        return oldItem.model.brandName == newItem.model.brandName &&
                oldItem.model.description == newItem.model.description
    }
}