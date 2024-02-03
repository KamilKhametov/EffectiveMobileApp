package com.khametov.effectivemobileapp.presentation.auth.vm

import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.presentation.auth.domain.AuthRepository
import com.khametov.effectivemobileapp.presentation.auth.domain.UserDataEntity
import com.khametov.effectivemobileapp.presentation.auth.intents.AuthViewEvent
import com.khametov.effectivemobileapp.presentation.auth.intents.AuthViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter,
    private val repository: AuthRepository
): BaseViewModel<AuthViewState, AuthViewEvent>(
    initialState = AuthViewState()
) {

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("router") router: CustomRouter
        ): AuthViewModel
    }

    override fun observe(event: AuthViewEvent) {
        when (event) {
            is AuthViewEvent.CheckIsAuth -> checkIsAuth(event.name, event.surname, event.phone)
        }
    }

    private fun checkIsAuth(
        name: String,
        surname: String,
        phone: String
    ) {

        launchCoroutine {

            repository.saveUserData(name, surname, phone)

            router.setRoot(Screens.flow())
        }
    }
}