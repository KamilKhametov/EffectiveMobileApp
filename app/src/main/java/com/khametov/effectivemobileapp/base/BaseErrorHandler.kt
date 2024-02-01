package com.khametov.effectivemobileapp.base

import com.khametov.effectivemobileapp.core.network.error.handler.ErrorHandler
import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorEntity
import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorWrapper


object BaseErrorHandler {

    private var errorHandler: ErrorHandler? = null
    private var errorWrapper: ErrorWrapper? = null

    fun mapException(throwable: Throwable): ErrorEntity {

        val wrapper = requireNotNull(errorWrapper) { "Error wrapper cannot be null" }

        return wrapper.getError(throwable)
    }

    fun handleException(errorEntity: ErrorEntity) {

        val handler = requireNotNull(errorHandler) { "Error handler cannot be null" }

        handler.handle(errorEntity = errorEntity)
    }

    fun init(errorHandler: ErrorHandler, errorWrapper: ErrorWrapper) {
        BaseErrorHandler.errorHandler = errorHandler
        BaseErrorHandler.errorWrapper = errorWrapper
    }
}