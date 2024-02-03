package com.khametov.effectivemobileapp.presentation.app.domain.repository

interface AppRepository {

    suspend fun userIsAuth(): Boolean
}