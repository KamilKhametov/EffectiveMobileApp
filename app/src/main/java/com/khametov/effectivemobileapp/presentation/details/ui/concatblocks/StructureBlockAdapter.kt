package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.khametov.R
import com.khametov.databinding.ItemProductStructureBlockBinding
import com.khametov.effectivemobileapp.presentation.adapter.BaseDelegateAdapter
import com.khametov.effectivemobileapp.presentation.adapter.DelegateAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

class StructureBlockAdapter:
    BaseDelegateAdapter<ItemProductStructureBlockBinding, ProductDetailsAdapterModel.StructureBlock>() {

    private var isHide = true

    override val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean
    ) -> ItemProductStructureBlockBinding = ItemProductStructureBlockBinding::inflate

    override fun BaseViewHolder<ItemProductStructureBlockBinding>.onBind(entity: ProductDetailsAdapterModel.StructureBlock) {
        with(viewBinding) {

            structureTextView.text = entity.title

            showMoreTextView.setOnClickListener {
                if (isHide) {
                    structureTextView.maxLines = Int.MAX_VALUE
                    showMoreTextView.text =
                        itemView.context.getString(R.string.kt_screen_hide_title)

                    isHide = false
                } else {
                    structureTextView.maxLines = 3
                    showMoreTextView.text =
                        itemView.context.getString(R.string.kt_screen_show_more_title)

                    isHide = true
                }
            }
        }
    }

    override fun BaseViewHolder<ItemProductStructureBlockBinding>.onBind(
        entity: ProductDetailsAdapterModel.StructureBlock,
        payloads: List<Any>
    ) {
        payloads.forEach { item ->
            if (item is ProductDetailsAdapterModel.StructureBlock) {
                onBind(item)
            }
        }
    }

    override fun isForItem(item: DelegateAdapter.ItemType): Boolean {
        return item is ProductDetailsAdapterModel.StructureBlock
    }

    override fun areItemsTheSame(
        oldItem: ProductDetailsAdapterModel.StructureBlock,
        newItem: ProductDetailsAdapterModel.StructureBlock
    ): Boolean {
        return true
    }

    override fun areContentsTheSame(
        oldItem: ProductDetailsAdapterModel.StructureBlock,
        newItem: ProductDetailsAdapterModel.StructureBlock
    ): Boolean {
        return oldItem.title == newItem.title
    }
}