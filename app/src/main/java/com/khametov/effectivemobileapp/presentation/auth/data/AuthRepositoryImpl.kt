package com.khametov.effectivemobileapp.presentation.auth.data

import com.khametov.effectivemobileapp.core.data.ClientManager
import com.khametov.effectivemobileapp.presentation.auth.domain.AuthRepository
import com.khametov.effectivemobileapp.presentation.auth.domain.UserDataEntity
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val clientManager: ClientManager
): AuthRepository {

    override suspend fun saveUserData(name: String, surname: String, phone: String) {
        clientManager.saveUserName(name)
        clientManager.saveUserSurname(surname)
        clientManager.saveUserPhone(phone)
    }
}