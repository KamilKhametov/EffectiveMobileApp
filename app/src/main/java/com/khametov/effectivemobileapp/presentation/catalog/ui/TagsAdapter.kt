package com.khametov.effectivemobileapp.presentation.catalog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.khametov.databinding.ItemTagBinding
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.TagModel

class TagsAdapter : ListAdapter<TagModel, TagsAdapter.TagViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TagViewHolder(
        itemBinding = ItemTagBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TagViewHolder(val itemBinding: ItemTagBinding) : ViewHolder(itemBinding.root){

        fun bind(model: TagModel) {

            itemBinding.tagTextView.text = model.name
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TagModel>() {
        override fun areItemsTheSame(
            oldItem: TagModel,
            newItem: TagModel,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TagModel,
            newItem: TagModel,
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.type == newItem.type
                    && oldItem.isClicked == newItem.isClicked
        }
    }
}