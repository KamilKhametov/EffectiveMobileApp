package com.khametov.effectivemobileapp.core.data

/**
 * Интерфейс для храненеия пользовательских данных
 */
interface ClientManager {

    suspend fun saveUserName(name: String)

    suspend fun getUserName(): String?

    suspend fun saveUserSurname(surname: String)

    suspend fun getUserSurname(): String?

    suspend fun saveUserPhone(phone: String)

    suspend fun getUserPhone(): String?
}