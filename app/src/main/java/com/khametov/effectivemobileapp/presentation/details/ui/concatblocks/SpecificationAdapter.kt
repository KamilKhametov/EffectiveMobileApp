package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khametov.databinding.ItemProductSpecificationBinding
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogInfoEntity

class SpecificationAdapter:
    ListAdapter<CatalogInfoEntity, SpecificationAdapter.SpecificationViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SpecificationViewHolder(
        itemBinding = ItemProductSpecificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SpecificationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SpecificationViewHolder(val itemBinding: ItemProductSpecificationBinding):
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: CatalogInfoEntity) {

            with(itemBinding) {

                specificationNameTextView.text = model.title
                specificationValueTextView.text = model.value
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<CatalogInfoEntity>() {

        override fun areItemsTheSame(
            oldItem: CatalogInfoEntity,
            newItem: CatalogInfoEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CatalogInfoEntity,
            newItem: CatalogInfoEntity
        ): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.value == newItem.value
        }
    }
}