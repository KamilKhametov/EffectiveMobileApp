package com.khametov.effectivemobileapp.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface DelegateAdapter {
    interface ItemType

    fun onCreateViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(baseViewHolder: RecyclerView.ViewHolder, item: ItemType)

    fun getChangePayload(oldItem: ItemType, newItem: ItemType): Any?

    fun isForItem(item: ItemType): Boolean

    fun checkItemsTheSame(oldItem: ItemType, newItem: ItemType): Boolean

    fun checkContentsTheSame(oldItem: ItemType, newItem: ItemType): Boolean

    fun onBindViewHolder(baseViewHolder: RecyclerView.ViewHolder, item: ItemType, payloads: List<Any>)
}