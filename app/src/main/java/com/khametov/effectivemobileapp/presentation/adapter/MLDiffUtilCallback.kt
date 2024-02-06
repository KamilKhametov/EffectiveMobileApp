package com.khametov.effectivemobileapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

class MLDiffUtilCallback(
    private val adapters: List<DelegateAdapter>,
    private val oldList: List<DelegateAdapter.ItemType>,
    private val newList: List<DelegateAdapter.ItemType>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]
        adapters.find { it.isForItem(oldElement) && it.isForItem(newElement) }?.let { adapter ->
            if (adapter.checkItemsTheSame(oldElement, newElement)) return true
        }
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]
        adapters.find { it.isForItem(oldElement) && it.isForItem(newElement) }?.let { adapter ->
            if (adapter.checkContentsTheSame(oldElement, newElement)) return true
        }
        return false
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]
        return adapters.find { it.isForItem(oldElement) && it.isForItem(newElement) }
            ?.getChangePayload(oldElement, newElement)
    }
}