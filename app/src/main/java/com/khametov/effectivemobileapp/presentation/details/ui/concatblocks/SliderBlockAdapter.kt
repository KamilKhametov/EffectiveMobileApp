package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import com.khametov.R
import com.khametov.databinding.ItemProductSliderBlockBinding
import com.khametov.effectivemobileapp.presentation.adapter.BaseDelegateAdapter
import com.khametov.effectivemobileapp.presentation.adapter.DelegateAdapter
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.ProductImageModel
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsAdapterModel

class SliderBlockAdapter : BaseDelegateAdapter<ItemProductSliderBlockBinding, ProductDetailsAdapterModel.SliderBlock>() {

    private val images = arrayListOf(
        ProductImageModel(
            id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
            image = listOf(R.drawable.britva, R.drawable.gel)
        ),
        ProductImageModel(
            id = "54a876a5-2205-48ba-9498-cfecff4baa6e",
            image = listOf(R.drawable.penka, R.drawable.lotion)
        ),
        ProductImageModel(
            id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
            image = listOf(R.drawable.gel, R.drawable.britva)
        ),
        ProductImageModel(
            id = "16f88865-ae74-4b7c-9d85-b68334bb97db",
            image = listOf(R.drawable.deco, R.drawable.care)
        ),
        ProductImageModel(
            id = "26f88856-ae74-4b7c-9d85-b68334bb97db",
            image = listOf(R.drawable.lotion, R.drawable.deco)
        ),
        ProductImageModel(
            id = "15f88865-ae74-4b7c-9d81-b78334bb97db",
            image = listOf(R.drawable.britva, R.drawable.penka)
        ),
        ProductImageModel(
            id = "88f88865-ae74-4b7c-9d81-b78334bb97db",
            image = listOf(R.drawable.care, R.drawable.deco)
        ),
        ProductImageModel(
            id = "55f58865-ae74-4b7c-9d81-b78334bb97db",
            image = listOf(R.drawable.penka, R.drawable.gel)
        )
    )

    override val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean
    ) -> ItemProductSliderBlockBinding = ItemProductSliderBlockBinding::inflate

    override fun BaseViewHolder<ItemProductSliderBlockBinding>.onBind(entity: ProductDetailsAdapterModel.SliderBlock) {

        with(viewBinding) {

            PagerSnapHelper().attachToRecyclerView(sliderRecyclerView)

            val necessaryImages = images.firstOrNull { it.id == entity.id }?.image

            val sliderImageAdapter = SliderImageAdapter()

            sliderRecyclerView.adapter = sliderImageAdapter

            pagerIndicatorContainer.attachToRecyclerView(sliderRecyclerView)

            sliderImageAdapter.submitList(necessaryImages)
        }
    }

    override fun BaseViewHolder<ItemProductSliderBlockBinding>.onBind(
        entity: ProductDetailsAdapterModel.SliderBlock,
        payloads: List<Any>
    ) {
        payloads.forEach { item ->
            if (item is ProductDetailsAdapterModel.SliderBlock) {
                onBind(item)
            }
        }
    }

    override fun isForItem(item: DelegateAdapter.ItemType): Boolean {
        return item is ProductDetailsAdapterModel.SliderBlock
    }

    override fun areItemsTheSame(
        oldItem: ProductDetailsAdapterModel.SliderBlock,
        newItem: ProductDetailsAdapterModel.SliderBlock
    ): Boolean {
        return true
    }

    override fun areContentsTheSame(
        oldItem: ProductDetailsAdapterModel.SliderBlock,
        newItem: ProductDetailsAdapterModel.SliderBlock
    ): Boolean {
        return oldItem.id == newItem.id
    }
}