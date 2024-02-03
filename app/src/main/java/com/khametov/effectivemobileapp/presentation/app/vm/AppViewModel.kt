package com.khametov.effectivemobileapp.presentation.app.vm

import com.github.terrakok.cicerone.NavigatorHolder
import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.core.FlowTab
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.presentation.app.domain.repository.AppRepository
import com.khametov.effectivemobileapp.presentation.app.events.AppViewEvent
import com.khametov.effectivemobileapp.presentation.app.events.AppViewState
import dagger.Lazy
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val router: CustomRouter,
    private val navigatorHolder: Lazy<NavigatorHolder>,
    private val repository: AppRepository
): BaseViewModel<AppViewState, AppViewEvent>(
    initialState = AppViewState()
) {

    override fun observe(event: AppViewEvent) {
        when (event) {
            is AppViewEvent.InitialTransition -> makeInitialTransaction()
            is AppViewEvent.RemoveNavigator   -> navigatorHolder.get().removeNavigator()
            is AppViewEvent.SetNavigator      -> navigatorHolder.get().setNavigator(navigator = event.navigator)
        }
    }

    private fun makeInitialTransaction() {

        launchCoroutine {

            if (repository.userIsAuth()) {
                router.setRoot(Screens.flow())
                router.switchNavTab(FlowTab.CATALOG.position)
            } else {
                router.setRoot(Screens.auth())
            }
        }
    }
}