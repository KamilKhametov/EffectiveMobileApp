package com.khametov.effectivemobileapp.presentation.catalog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.khametov.R
import com.khametov.databinding.ItemTagBinding
import com.khametov.effectivemobileapp.common.extension.makeGone
import com.khametov.effectivemobileapp.common.extension.makeVisible
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.TagModel

class TagsAdapter(
    private val onTagClick: (model: TagModel) -> Unit,
    private val cancelSort: () -> Unit
) : ListAdapter<TagModel, TagsAdapter.TagViewHolder>(DiffCallback()) {

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

    inner class TagViewHolder(val itemBinding: ItemTagBinding) : ViewHolder(itemBinding.root){

        fun bind(model: TagModel) {

            with(itemBinding) {

                tagTextView.text = model.name

                if (model.isClicked) {

                    tagContainer.setBackgroundResource(R.drawable.background_dark_blue_round_100)
                    tagTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    cancelImageView.makeVisible()
                } else {

                    tagContainer.setBackgroundResource(R.drawable.background_light_gray_round_100)
                    tagTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.grey))
                    cancelImageView.makeGone()
                }

                itemView.setOnClickListener {
                    onTagClick(model)
                }

                cancelImageView.setOnClickListener {
                    cancelSort()
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TagModel>() {
        override fun areItemsTheSame(
            oldItem: TagModel,
            newItem: TagModel,
        ): Boolean {
            return oldItem.id == newItem.id
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