package com.khametov.effectivemobileapp.core.network.error.handler

import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorEntity

/**
 * Сущность обработки спамленных ошибок,
 * т.е. скармливается смапленная ошибка
 * и в функции [handle] определяется как нужно реагировать на ту или иную ошибку.
 */
interface ErrorHandler {
    fun handle(errorEntity: ErrorEntity)
}