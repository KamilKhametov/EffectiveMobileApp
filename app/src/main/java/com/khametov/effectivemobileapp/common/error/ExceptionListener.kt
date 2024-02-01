package com.khametov.effectivemobileapp.common.error

import com.khametov.effectivemobileapp.core.network.error.parsing.ErrorEntity


/**
 * Функциональный интерфейс для обработчика ошибок, который возвращает boolean.
 * Возвращаемый boolean указывает, хотим ли мы обработать ошибку вручную
 *  true - Ошибка будет обработана вручную.
 *  false - Ошибка будет обработана атвоматически
 *
 *  * пример
 * ```
 * fun onExceptionActions() = ExceptionListener {
 *      // действия для обработки ошибки
 *      true
 * }
 *
 */
fun interface ExceptionListener {
    fun consume(entity: ErrorEntity): Boolean
}