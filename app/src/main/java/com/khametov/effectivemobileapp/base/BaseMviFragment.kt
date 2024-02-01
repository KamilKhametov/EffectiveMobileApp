package com.khametov.effectivemobileapp.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

abstract class BaseMviFragment<S : BaseViewState, E : BaseViewEvent, VM : BaseViewModel<S, E>>(
    @LayoutRes layoutResId: Int,
) : BaseFragment(layoutResId = layoutResId) {

    protected abstract val viewModel: VM
    protected abstract fun render(state: S)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState().flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect(::render)
        }
    }
}