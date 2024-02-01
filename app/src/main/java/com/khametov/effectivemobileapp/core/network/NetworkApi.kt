package com.khametov.effectivemobileapp.core.network

import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorWrapper

interface NetworkApi {

    /**
     * Предоставление апи класса для использования
     */
    fun provideApiClass(): RestApi


//    fun <T>provideApiClass(apiInterface: T): T


    /**
     * Предоставление маппера ошибок для использования
     */
    fun provideErrorWrapper(): ErrorWrapper
}