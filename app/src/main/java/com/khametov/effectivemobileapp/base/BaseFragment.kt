package com.khametov.effectivemobileapp.base

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.khametov.effectivemobileapp.common.extension.orZero
import com.khametov.effectivemobileapp.core.navigation.core.TransitionType
import com.khametov.effectivemobileapp.core.navigation.router.RouterProvider

abstract class BaseFragment(@LayoutRes layoutResId: Int): Fragment(layoutResId) {

    open val transitionType: TransitionType = TransitionType.HORIZONTAL

    private var keyboardListenersAttached = false
    private var rootLayout: ViewGroup? = null
    private var isDetachKeyboardObserver = false
    private var isShowKeyboard = false

    protected open fun onShowKeyboard() {}
    protected open fun onHideKeyboard() {}

    open fun showNavigationMenu(show: Boolean) {
        (parentFragment as? BaseFragment)?.showNavigationMenu(show = show)
    }

    open fun switchNavigationTab(position: Int) {
        (parentFragment as? BaseFragment)?.switchNavigationTab(position = position)
    }

    protected val tabRouter
        get() = (parentFragment as? RouterProvider)?.router
            ?: (requireActivity() as RouterProvider).router

    protected open fun setupInjection() = Unit
    protected open fun setupUi() = Unit

    override fun onAttach(context: Context) {
        setupInjection()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRealDestroy()) {
            onFinalDestroy()
        }
    }

    abstract fun onBackPressed(): Boolean

    open fun onFinalDestroy() = Unit

    private fun isRealDestroy(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true              -> true
            else                                       -> isRealRemoving()
        }

    private fun isRealRemoving(): Boolean {
        return (isRemoving) ||
                ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)
    }

    protected open fun attachKeyboardObserver() {
        if (keyboardListenersAttached) return

        rootLayout = activity?.findViewById(android.R.id.content) as ViewGroup?
        rootLayout?.viewTreeObserver?.addOnGlobalLayoutListener(keyboardLayoutObserver)
        keyboardListenersAttached = true
    }

    protected open fun detachKeyBoardObserver() {
        if (keyboardListenersAttached) {
            keyboardListenersAttached = false
            rootLayout?.viewTreeObserver?.removeOnGlobalLayoutListener(keyboardLayoutObserver)
        }
    }

    private val keyboardLayoutObserver = ViewTreeObserver.OnGlobalLayoutListener {

        val r = Rect()
        rootLayout?.getWindowVisibleDisplayFrame(r)

        val screenHeight = rootLayout?.rootView?.height.orZero()
        val keypadHeight = screenHeight - r.bottom

        val isHideKeyboard = keypadHeight < screenHeight * 0.15 && isShowKeyboard
        isShowKeyboard = keypadHeight > screenHeight * 0.15

        if (keyboardListenersAttached) {
            if (isShowKeyboard) onShowKeyboard()
            if (isHideKeyboard) onHideKeyboard()
        }
    }
}