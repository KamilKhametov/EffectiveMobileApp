package com.khametov.effectivemobileapp.common.error.coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import com.khametov.effectivemobileapp.base.BaseErrorHandler
import com.khametov.effectivemobileapp.common.error.ExceptionListener
import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorEntity
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

internal class CoroutineErrorHandler(
    private val exceptionListener: ExceptionListener,
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        val error = BaseErrorHandler.mapException(throwable = exception)
        val handleManually = exceptionListener.consume(entity = error)

        Log.d("REQUEST_ERROR", "$exception")

        if (handleManually.not() || error is ErrorEntity.Unauthorized) {
            BaseErrorHandler.handleException(errorEntity = error)
        }
    }
}