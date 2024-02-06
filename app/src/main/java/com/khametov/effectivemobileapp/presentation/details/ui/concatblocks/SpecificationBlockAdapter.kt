package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.khametov.databinding.ItemProductSpecificationBlockBinding
import com.khametov.effectivemobileapp.presentation.adapter.BaseDelegateAdapter
import com.khametov.effectivemobileapp.presentation.adapter.DelegateAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

class SpecificationBlockAdapter:
    BaseDelegateAdapter<ItemProductSpecificationBlockBinding, ProductDetailsAdapterModel.SpecificationBlock>() {

    override val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean
    ) -> ItemProductSpecificationBlockBinding = ItemProductSpecificationBlockBinding::inflate

    override fun BaseViewHolder<ItemProductSpecificationBlockBinding>.onBind(entity: ProductDetailsAdapterModel.SpecificationBlock) {
        with(viewBinding) {

            val specificationAdapter = SpecificationAdapter()

            specificationsRecyclerView.adapter = specificationAdapter
            specificationAdapter.submitList(entity.model.specificationList)
        }
    }

    override fun BaseViewHolder<ItemProductSpecificationBlockBinding>.onBind(
        entity: ProductDetailsAdapterModel.SpecificationBlock,
        payloads: List<Any>
    ) {
        payloads.forEach { item ->
            if (item is ProductDetailsAdapterModel.SpecificationBlock) {
                onBind(item)
            }
        }
    }

    override fun isForItem(item: DelegateAdapter.ItemType): Boolean {
        return item is ProductDetailsAdapterModel.SpecificationBlock
    }

    override fun areItemsTheSame(
        oldItem: ProductDetailsAdapterModel.SpecificationBlock,
        newItem: ProductDetailsAdapterModel.SpecificationBlock
    ): Boolean {
        return true
    }

    override fun areContentsTheSame(
        oldItem: ProductDetailsAdapterModel.SpecificationBlock,
        newItem: ProductDetailsAdapterModel.SpecificationBlock
    ): Boolean {
        return oldItem.model.specificationList == newItem.model.specificationList
    }
}