package com.khametov.effectivemobileapp.core.network.error.handler

import com.khametov.R
import com.khametov.effectivemobileapp.common.resourcemanager.ResourceManager
import com.khametov.effectivemobileapp.core.network.error.callback.ErrorCallback
import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorEntity
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(
    private val callBack: ErrorCallback,
    private val resourceManager: ResourceManager,
) : ErrorHandler {

    override fun handle(errorEntity: ErrorEntity) {

        if (errorEntity is ErrorEntity.Unauthorized) {
            callBack.onAuthError()
            return
        }

        val message = when (errorEntity) {
            is ErrorEntity.Network     -> resourceManager.getString(R.string.error_server_network)
            is ErrorEntity.ServerError -> resourceManager.getString(R.string.error_server_internal)
            is ErrorEntity.Timeout     -> resourceManager.getString(R.string.error_server_timeout)
            is ErrorEntity.CustomError -> errorEntity.message
            else                       -> resourceManager.getString(R.string.error_server_unknown)
        }

        callBack.onOrdinalError(message = message)
    }
}