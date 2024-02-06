package com.khametov.effectivemobileapp.core.data

import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity

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

    suspend fun saveOrDeleteFavorites(isSave: Boolean, model: CatalogItemEntity)

    suspend fun getFavorites(): List<CatalogItemEntity>?

    suspend fun clearUserData()
}