package com.khametov.effectivemobileapp.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MLConcatAdapter private constructor(
    adaptersAsList: List<DelegateAdapter>,
    vararg adapters: DelegateAdapter,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapters = mutableListOf<DelegateAdapter>()
    private var elements = mutableListOf<DelegateAdapter.ItemType>()

    constructor(vararg adapters: DelegateAdapter) : this(
        adaptersAsList = emptyList(),
        adapters = adapters
    )

    constructor(adapters: List<DelegateAdapter>) : this(
        adapters = arrayOf(),
        adaptersAsList = adapters
    )

    init {
        this.adapters.run {
            addAll(adapters.toList())
            addAll(adaptersAsList)
        }
    }

    override fun getItemCount() = elements.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        adapters[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        adapters[getItemViewType(position)].onBindViewHolder(holder, elements[position])

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            adapters[getItemViewType(position)].onBindViewHolder(holder, elements[position], payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        adapters.forEachIndexed { index, adapter ->
            if (adapter.isForItem(elements[position])) return index
        }
        throw IllegalStateException("Can`t get viewType for position $position")
    }

    fun add(vararg objects: DelegateAdapter.ItemType) {
        elements.addAll(objects)
        notifyItemRangeChanged(elements.size, objects.size)
    }

    fun add(objects: List<DelegateAdapter.ItemType>) {
        elements.addAll(objects)
        notifyItemRangeChanged(elements.size, objects.size)
    }

    fun removeLast() {
        elements.remove(elements.last())
        notifyItemRemoved(elements.size)
    }

    fun updateItem(item: DelegateAdapter.ItemType, position: Int) {
        elements[position] = item
        notifyItemChanged(position)
    }

    fun setItems(objects: List<DelegateAdapter.ItemType>) {

        val diffUtils = MLDiffUtilCallback(adapters.toList(), elements.toList(), objects)
        elements.clear()
        elements.addAll(objects)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItems(): List<DelegateAdapter.ItemType> = elements
    fun getDelegates(): List<DelegateAdapter> = adapters.toList()
}