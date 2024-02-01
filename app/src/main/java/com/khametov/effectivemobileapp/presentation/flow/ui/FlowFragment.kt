package com.khametov.effectivemobileapp.presentation.flow.ui

import android.os.Handler
import android.os.Looper
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.khametov.R
import com.khametov.databinding.FragmentFlowBinding
import com.khametov.effectivemobileapp.base.BaseContainerFragment
import com.khametov.effectivemobileapp.base.BaseFragment
import com.khametov.effectivemobileapp.base.BaseMviFragment
import com.khametov.effectivemobileapp.common.extension.toast
import com.khametov.effectivemobileapp.common.extension.viewModels
import com.khametov.effectivemobileapp.core.navigation.core.FlowTab
import com.khametov.effectivemobileapp.core.navigation.core.TabContainerFragment
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.presentation.flow.events.FlowViewEvent
import com.khametov.effectivemobileapp.presentation.flow.events.FlowViewState
import com.khametov.effectivemobileapp.presentation.flow.vm.FlowViewModel
import com.khametov.effectivemobileapp.presentation.flow.FlowFeature
import javax.inject.Inject

class FlowFragment: BaseMviFragment<FlowViewState, FlowViewEvent, FlowViewModel>(
    layoutResId = R.layout.fragment_flow
) {

    @Inject
    lateinit var mainFlowRouter: CustomRouter

    private val viewBinding by viewBinding(FragmentFlowBinding::class.java)
    override val viewModel by viewModels(FlowFeature.getComponent()::provideFlowViewModel)

    private var isTabBackStack = false
    private var lastTabPosition = 0
    private var doubleBackToExitPressedOnce = false

    override fun setupInjection() {
        FlowFeature.getComponent().inject(this)
    }

    override fun render(state: FlowViewState) {
        renderTabSelection(tab = state.currentTab)
    }

    override fun setupUi() {

        initItemSelectionListener()
    }

    override fun switchNavigationTab(position: Int) {

        val tabItem = (viewBinding.bottomNav.getChildAt(0) as? BottomNavigationMenuView)
            ?.getChildAt(position) as? BottomNavigationItemView
            ?: throw IllegalArgumentException("Failed to get itemView by $position")

        if (isTabBackStack.not()) {
            addTabHistory(lastTabPosition)
        } else isTabBackStack = false

        lastTabPosition = position

        tabItem.performClick()
    }

    override fun showNavigationMenu(show: Boolean) {
        viewBinding.bottomNav.isVisible = show
    }

    private fun initItemSelectionListener() {

        viewBinding.bottomNav.itemIconTintList = null

        viewBinding.bottomNav.setOnItemSelectedListener { item ->

            val selectedTab = FlowTab.getBy(itemId = item.itemId)

            if (selectedTab == viewModel.viewState().value.currentTab) {
                resetCurrentStack()
            } else {
                viewModel.perform(viewEvent = FlowViewEvent.TabSelect(tab = selectedTab))
            }
            true
        }
    }

    private fun renderTabSelection(tab: FlowTab) {

        val currentFragment = childFragmentManager.fragments.find(Fragment::isVisible)
        val newFragment = childFragmentManager.findFragmentByTag(tab.containerTag)

        if (currentFragment != null
            && currentFragment === newFragment
            && currentFragment is TabContainerFragment
        ) {
            return
        }

        val transaction = childFragmentManager.beginTransaction()

        if (newFragment == null) {
            transaction.add(
                R.id.container,
                Screens.tabScreen(
                    containerTag = tab.containerTag,
                    position = tab.position
                ).createFragment(childFragmentManager.fragmentFactory),
                tab.containerTag
            )
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        transaction.commitNow()
    }

    private fun resetCurrentStack() {

        childFragmentManager.fragments.find(Fragment::isVisible)?.let { fragment ->
            if (fragment is BaseContainerFragment) {
                fragment.resetStack()
            }
        }
    }

    private fun addTabHistory(tabPos: Int) {
        tabTransitionsHistory.add(tabPos)
        if (tabTransitionsHistory.size > LIMIT_TAB_HISTORY)
            tabTransitionsHistory.removeAt(0)
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.fragments.firstOrNull { it.isVisible }
        return if (fragment != null
            && fragment is BaseFragment
            && (fragment.onBackPressed())
        ) {
            true
        } else {
            checkForTabHistory()
            true
        }
    }

    private fun checkForTabHistory() {

        if (tabTransitionsHistory.isNotEmpty()) {
            isTabBackStack = true
            switchNavigationTab(tabTransitionsHistory.last())
            tabTransitionsHistory.removeAt(tabTransitionsHistory.size - 1)
        } else {
            if (doubleBackToExitPressedOnce) {
                mainFlowRouter.back()
            }

            doubleBackToExitPressedOnce = true
            toast(R.string.click_double_for_exit)

            Handler(Looper.getMainLooper()).postDelayed(
                { doubleBackToExitPressedOnce = false },
                EXIT_PRESS_DELAYER_MILLIS
            )
        }
    }

    companion object {

        fun newInstance() = FlowFragment()

        private val tabTransitionsHistory = ArrayList<Int>(6)

        private const val LIMIT_TAB_HISTORY = 5

        private const val EXIT_PRESS_DELAYER_MILLIS = 2000L
    }
}