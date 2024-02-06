package com.khametov.effectivemobileapp.presentation.profile.data.repository

import com.khametov.effectivemobileapp.common.extension.orZero
import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.presentation.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val clientManager: ClientManager
): ProfileRepository {

    override suspend fun getUserFullName(): String {
        return "${clientManager.getUserName()} ${clientManager.getUserSurname()}"
    }

    override suspend fun getUserPhone(): String {
        return clientManager.getUserPhone().orEmpty()
    }

    override suspend fun getFavoritesCount(): Int {
        return clientManager.getFavorites()?.size.orZero()
    }

    override suspend fun clearUserData() {
        clientManager.clearUserData()
    }
}