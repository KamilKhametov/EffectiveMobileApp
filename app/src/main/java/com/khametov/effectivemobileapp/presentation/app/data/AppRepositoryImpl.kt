package com.khametov.effectivemobileapp.presentation.app.data

import com.khametov.effectivemobileapp.common.extension.isNotNull
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.presentation.app.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val clientManager: ClientManager
): AppRepository {

    override suspend fun userIsAuth(): Boolean {
        return clientManager.getUserName().isNotNull() &&
                clientManager.getUserSurname().isNotNull() &&
                clientManager.getUserPhone().isNotNull()
    }
}