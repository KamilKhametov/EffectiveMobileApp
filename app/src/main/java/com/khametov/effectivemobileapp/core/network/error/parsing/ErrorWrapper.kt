package com.khametov.effectivemobileapp.core.network.error.parsing

interface ErrorWrapper {

    fun getError(throwable: Throwable): ErrorEntity
}