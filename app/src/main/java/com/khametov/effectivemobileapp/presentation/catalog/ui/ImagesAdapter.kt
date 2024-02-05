package com.khametov.effectivemobileapp.presentation.catalog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.khametov.databinding.ItemImageBinding

class ImagesAdapter: ListAdapter<Int, ImagesAdapter.ImageViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        itemBinding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImageViewHolder(val itemBinding: ItemImageBinding): ViewHolder(itemBinding.root) {

        fun bind(image: Int) {

            itemBinding.productImageView.load(image)
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Int>() {

        override fun areItemsTheSame(
            oldItem: Int,
            newItem: Int,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Int,
            newItem: Int,
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}