package com.khametov.effectivemobileapp.presentation.catalog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.khametov.R
import com.khametov.databinding.ItemProductBinding
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.ProductImageModel

class ProductsAdapter:
    ListAdapter<CatalogItemEntity, ProductsAdapter.CatalogProductViewHolder>(DiffCallback()) {

    private val images = arrayListOf(
        ProductImageModel(
            id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
            image = listOf(R.drawable.icv_favorite_on)
        ),
        ProductImageModel(
            id = "54a876a5-2205-48ba-9498-cfecff4baa6e",
            image = listOf(R.drawable.icv_favorite_on)
        ),
        ProductImageModel(
            id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
            image = listOf(R.drawable.icv_favorite_on)
        ),
        ProductImageModel(
            id = "16f88865-ae74-4b7c-9d85-b68334bb97db",
            image = listOf(R.drawable.icv_favorite_on)
        ),
        ProductImageModel(
            id = "26f88856-ae74-4b7c-9d85-b68334bb97db",
            image = listOf(R.drawable.icv_favorite_on)
        ),
        ProductImageModel(
            id = "15f88865-ae74-4b7c-9d81-b78334bb97db",
            image = listOf(R.drawable.icv_favorite_on)
        ),
        ProductImageModel(
            id = "88f88865-ae74-4b7c-9d81-b78334bb97db",
            image = listOf(R.drawable.icv_favorite_on)
        ),
        ProductImageModel(
            id = "55f58865-ae74-4b7c-9d81-b78334bb97db",
            image = listOf(R.drawable.icv_favorite_on)
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatalogProductViewHolder(
        itemBinding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CatalogProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CatalogProductViewHolder(val itemBinding: ItemProductBinding):
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: CatalogItemEntity) {

            with(itemBinding) {

                oldPriceTextView.text = model.price.priceWithDiscount
                priceTextView.text = model.price.price
                discountTextView.text = model.price.discount.toString()
                brandTextView.text = model.title
                descriptionTextView.text = model.subtitle
                ratingAndReviewsCountTextView.text = itemView.context.getString(
                    R.string.catalog_screen_rating_and_reviews_count,
                    model.feedback.rating.toString(),
                    model.feedback.count.toString()
                )
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<CatalogItemEntity>() {

        override fun areItemsTheSame(
            oldItem: CatalogItemEntity,
            newItem: CatalogItemEntity,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CatalogItemEntity,
            newItem: CatalogItemEntity,
        ): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.subtitle == newItem.subtitle
                    && oldItem.price == newItem.price
                    && oldItem.feedback == newItem.feedback
                    && oldItem.tags == newItem.tags
                    && oldItem.available == newItem.available
                    && oldItem.description == newItem.description
                    && oldItem.info == newItem.info
                    && oldItem.ingredients == newItem.ingredients
        }
    }
}