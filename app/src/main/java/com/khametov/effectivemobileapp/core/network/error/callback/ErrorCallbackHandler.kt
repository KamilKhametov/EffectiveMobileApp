package com.khametov.effectivemobileapp.core.network.error.callback


import com.khametov.effectivemobileapp.core.navigation.bundle.MessageBundle
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import javax.inject.Inject

class ErrorCallbackHandler @Inject constructor(
    private val router: CustomRouter
) : ErrorCallback {

    override fun onAuthError() {
        router.restartApp()
    }

    override fun onOrdinalError(message: String) {

        router.showMessage(
            bundle = MessageBundle.Builder
                .asError()
                .showShort()
                .setMessageString(message)
                .build()
        )
    }
}