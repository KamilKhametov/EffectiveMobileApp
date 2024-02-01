package com.khametov.effectivemobileapp.common.resourcemanager

interface ResourceManager {
    fun getString(resourceId: Int): String
    fun getColor(resourceId: Int): Int
    fun getDeviceId(): String
}