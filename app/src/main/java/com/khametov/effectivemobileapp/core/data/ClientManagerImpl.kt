package com.khametov.effectivemobileapp.core.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khametov.effectivemobileapp.base.BaseStoragePreferences
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class ClientManagerImpl @Inject constructor(
    context: Context
): BaseStoragePreferences(PREF_NAME, context), ClientManager {

    override suspend fun saveUserName(name: String) {
        putString(PREF_USER_NAME, name)
    }

    override suspend fun getUserName(): String? {
        return readString(PREF_USER_NAME, null)
    }

    override suspend fun saveUserSurname(surname: String) {
        putString(PREF_USER_SURNAME, surname)
    }

    override suspend fun getUserSurname(): String? {
        return readString(PREF_USER_SURNAME, null)
    }

    override suspend fun saveUserPhone(phone: String) {
        putString(PREF_USER_PHONE, phone)
    }

    override suspend fun getUserPhone(): String? {
        return readString(PREF_USER_PHONE, null)
    }

    override suspend fun saveOrDeleteFavorites(isSave: Boolean, model: CatalogItemEntity) {

        val favoritesList = getFavorites().orEmpty().toMutableList()

        if (isSave) {
            favoritesList.add(model)
        } else {
            val a = favoritesList.indexOfFirst { it.id == model.id }
            favoritesList.removeAt(a)
        }

        putJson(
            PREF_FAVORITES,
            JSONObject().put(PREF_FAVORITES, JSONArray(Gson().toJson(favoritesList)))
        )
    }

    override suspend fun getFavorites(): List<CatalogItemEntity>? {

        var favoritesList: List<CatalogItemEntity>? = null

        readJson(PREF_FAVORITES)?.let { jsonObject ->

            val jsonArray = jsonObject.get(PREF_FAVORITES) ?: false
            val listFromJson = GsonBuilder().create()
                .fromJson(jsonArray.toString(), Array<CatalogItemEntity>::class.java)
                .orEmpty()
                .toList()

            if (listFromJson.isNotEmpty()) favoritesList = listFromJson
        }

        return favoritesList?.distinct()
    }

    override suspend fun clearUserData() {
        clearKey(PREF_USER_NAME)
        clearKey(PREF_USER_SURNAME)
        clearKey(PREF_USER_PHONE)
        clearKey(PREF_FAVORITES)
    }

    private companion object {

        private const val PREF_NAME = "CLIENT_PREFERENCES_NAME"

        private const val PREF_USER_NAME = "PREF_USER_NAME"

        private const val PREF_USER_SURNAME = "PREF_USER_SURNAME"

        private const val PREF_USER_PHONE = "PREF_USER_PHONE"

        private const val PREF_FAVORITES = "PREF_FAVORITES"
    }
}