package com.khametov.effectivemobileapp.presentation.details.ui.concatblocks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.khametov.databinding.ItemProductSliderImageBinding

class SliderImageAdapter : ListAdapter<Int, SliderImageAdapter.SliderImageViewHolder>(
DiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SliderImageViewHolder(
        itemBinding = ItemProductSliderImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SliderImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SliderImageViewHolder(val itemBinding: ItemProductSliderImageBinding):
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(image: Int) {

            itemBinding.productImageView.load(image)
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}