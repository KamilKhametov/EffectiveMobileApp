package com.khametov.effectivemobileapp.presentation.profile.intents

import com.khametov.effectivemobileapp.base.BaseViewState

data class ProfileViewState(
    val userFullName: String? = null,
    val userPhone: String? = null,
    val favoritesCount: Int? = null
): BaseViewState {
}