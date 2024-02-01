package com.khametov.effectivemobileapp.core.data

import android.content.Context
import com.khametov.effectivemobileapp.base.BaseStoragePreferences
import javax.inject.Inject

class ClientManagerImpl @Inject constructor(
    context: Context
): BaseStoragePreferences(PREF_NAME, context), ClientManager {

    private companion object {

        private const val PREF_NAME = "CLIENT_PREFERENCES_NAME"
    }
}