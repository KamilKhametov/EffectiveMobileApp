package com.khametov.effectivemobileapp.presentation.app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.khametov.R
import com.khametov.effectivemobileapp.App
import com.khametov.effectivemobileapp.base.BaseActivity
import com.khametov.effectivemobileapp.base.BaseFragment
import com.khametov.effectivemobileapp.common.extension.uiLazy
import com.khametov.effectivemobileapp.core.navigation.bundle.MessageBundle
import com.khametov.effectivemobileapp.core.navigation.navigator.CustomNavigator
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.presentation.app.events.AppViewEvent
import com.khametov.effectivemobileapp.presentation.app.vm.AppViewModel
import javax.inject.Inject

class AppActivity: BaseActivity(layoutRes = R.layout.activity_main) {

    @Inject
    lateinit var viewModel: AppViewModel

    @Inject
    lateinit var appRouter: CustomRouter

    override val router: CustomRouter get() = appRouter

    private val navigator by uiLazy {
        CustomNavigator(
            navigatorActivity = this,
            containerId = R.id.appContainer
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.app.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.perform(viewEvent = AppViewEvent.InitialTransition)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.perform(viewEvent = AppViewEvent.SetNavigator(navigator = navigator))
    }

    override fun onPause() {
        viewModel.perform(viewEvent = AppViewEvent.RemoveNavigator)
        super.onPause()
    }

    override fun switchNavTab(position: Int) {

        supportFragmentManager.fragments.firstOrNull()?.let { fragment ->
            if (fragment is BaseFragment) {
                fragment.switchNavigationTab(position = position)
            }
        }
    }

    override fun onBackPressed() {

        val fragment = supportFragmentManager.fragments.find(Fragment::isVisible)

        if (fragment != null && fragment is BaseFragment && fragment.onBackPressed()) {
            return
        } else {
            appRouter.back()
        }
    }

    override fun showMessage(bundle: MessageBundle) {

        val text = bundle.messageRes?.let { getString(it) } ?: bundle.messageText

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}