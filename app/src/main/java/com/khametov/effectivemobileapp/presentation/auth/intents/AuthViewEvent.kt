package com.khametov.effectivemobileapp.presentation.auth.intents

import com.khametov.effectivemobileapp.base.BaseViewEvent

sealed class AuthViewEvent: BaseViewEvent {
    data class CheckIsAuth(
        val name: String,
        val surname: String,
        val phone: String
    ): AuthViewEvent()
}