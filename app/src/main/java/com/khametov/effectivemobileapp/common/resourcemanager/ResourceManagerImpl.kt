package com.khametov.effectivemobileapp.common.resourcemanager

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.khametov.effectivemobileapp.App
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    private val context: Context,
) : ResourceManager {

    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }

    override fun getColor(resourceId: Int): Int {
        return context.getColor(resourceId)
    }

    @SuppressLint("HardwareIds")
    override fun getDeviceId(): String {
        return Settings.Secure.getString(
            App.app.applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }
}