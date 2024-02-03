package com.khametov.effectivemobileapp.core.data

import android.content.Context
import com.khametov.effectivemobileapp.base.BaseStoragePreferences
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

    private companion object {

        private const val PREF_NAME = "CLIENT_PREFERENCES_NAME"

        private const val PREF_USER_NAME = "PREF_USER_NAME"

        private const val PREF_USER_SURNAME = "PREF_USER_SURNAME"

        private const val PREF_USER_PHONE = "PREF_USER_PHONE"
    }
}