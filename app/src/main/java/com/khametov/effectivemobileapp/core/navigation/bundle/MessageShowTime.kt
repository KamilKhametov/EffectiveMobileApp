package com.khametov.effectivemobileapp.core.navigation.bundle

/**
 * Типы продолжительности показа сообщения
 * @param millis длительность показа сообщения в мс
 */
enum class MessageShowTime(val millis: Int) {
    SHORT(millis = 3000),
    LONG(millis = 6000);
}