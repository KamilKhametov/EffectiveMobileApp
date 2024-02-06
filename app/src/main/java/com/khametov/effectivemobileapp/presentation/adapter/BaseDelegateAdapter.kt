package com.khametov.effectivemobileapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseDelegateAdapter<V : ViewBinding, T : DelegateAdapter.ItemType> :
    DelegateAdapter {

    abstract val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean,
    ) -> V

    @Suppress("UNCHECKED_CAST")
    val BaseViewHolder<V>.item: T?
        get() = this.item as? T

    open fun BaseViewHolder<V>.onCreated() {}

    open fun BaseViewHolder<V>.onBind(entity: T) {}

    open fun BaseViewHolder<V>.onBind(entity: T, payloads: List<Any>) {}

    open fun findChangePayload(oldItem: T, newItem: T): Any? = null

    open fun areItemsTheSame(oldItem: T, newItem: T) = false

    open fun areContentsTheSame(oldItem: T, newItem: T) = false

    final override fun onCreateViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder =
        BaseViewHolder(viewBindingInflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
            .apply { onCreated() }

    @Suppress("UNCHECKED_CAST")
    final override fun onBindViewHolder(baseViewHolder: RecyclerView.ViewHolder, item: DelegateAdapter.ItemType) =
        (baseViewHolder as BaseViewHolder<V>).onBind(item as T)

    @Suppress("UNCHECKED_CAST")
    final override fun onBindViewHolder(
        baseViewHolder: RecyclerView.ViewHolder,
        item: DelegateAdapter.ItemType,
        payloads: List<Any>
    ) = (baseViewHolder as BaseViewHolder<V>).onBind(item as T, payloads)

    @Suppress("UNCHECKED_CAST")
    final override fun checkItemsTheSame(
        oldItem: DelegateAdapter.ItemType,
        newItem: DelegateAdapter.ItemType,
    ): Boolean = areItemsTheSame(oldItem as T, newItem as T)

    @Suppress("UNCHECKED_CAST")
    final override fun checkContentsTheSame(
        oldItem: DelegateAdapter.ItemType,
        newItem: DelegateAdapter.ItemType,
    ): Boolean = areContentsTheSame(oldItem as T, newItem as T)

    @Suppress("UNCHECKED_CAST")
    final override fun getChangePayload(
        oldItem: DelegateAdapter.ItemType,
        newItem: DelegateAdapter.ItemType
    ): Any? = findChangePayload(oldItem as T, newItem as T)

    class BaseViewHolder<V : ViewBinding>(
        val viewBinding: V,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        internal var item: DelegateAdapter.ItemType? = null
            private set

        val context: Context
            get() = viewBinding.root.context

        fun setThrottleClickListener(onClick: () -> Unit) {
            viewBinding.root.setOnClickListener { onClick() }
        }
    }
}