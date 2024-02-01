package com.khametov.effectivemobileapp.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.strictmode.FragmentStrictMode
import androidx.viewbinding.BuildConfig
import com.github.terrakok.cicerone.Cicerone
import com.khametov.effectivemobileapp.base.BaseActivity
import com.khametov.effectivemobileapp.core.navigation.navigator.CustomNavigator
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouterImpl
import com.khametov.effectivemobileapp.core.navigation.router.RouterProvider

abstract class BaseContainerFragment(@LayoutRes layoutResId: Int) : BaseFragment(
    layoutResId = layoutResId
) , RouterProvider {

    abstract val cicerone: Cicerone<CustomRouterImpl>
    abstract val containerResId: Int

    override val router: CustomRouter
        get() = cicerone.router

    private val navigator by lazy(LazyThreadSafetyMode.NONE) {
        CustomNavigator(
            navigatorActivity = requireActivity() as BaseActivity,
            containerId = containerResId,
            fragmentManager = childFragmentManager
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (BuildConfig.DEBUG) {
            setupStrictMode()
        }
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(containerResId)
        return fragment != null && fragment is BaseFragment && fragment.onBackPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (childFragmentManager.findFragmentById(containerResId) == null) {
            executeTransactions()
        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    abstract fun executeTransactions()

    open fun resetStack() {
        router.goBackTo(null)
    }

    private fun setupStrictMode() {

        childFragmentManager.strictModePolicy = FragmentStrictMode.Policy.Builder()
            .penaltyDeath()
            .detectFragmentReuse()
            .detectFragmentTagUsage()
            .detectWrongFragmentContainer()
            .build()
    }
}