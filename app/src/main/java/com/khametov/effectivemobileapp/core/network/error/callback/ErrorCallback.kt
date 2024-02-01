package com.khametov.effectivemobileapp.core.network.error.callback

/**
 * Интфейс целевых действий при той или иной ошибке
 */
interface ErrorCallback {
    fun onAuthError()
    fun onOrdinalError(message: String)
}