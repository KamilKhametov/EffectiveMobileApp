package com.khametov.effectivemobileapp.core.navigation.bundle

import androidx.annotation.StringRes

/**
 * Объект для показа сообщения
 *
 * @property messageRes текст сообщения как ресурс
 * @property messageText текст сообщения как строка
 * @property type тип сообщения
 * @property showTime длительность показа сообщения
 */
data class MessageBundle(
    val messageRes: Int? = null,
    val messageText: String? = null,
    val type: MessageType = MessageType.INFO,
    val showTime: MessageShowTime = MessageShowTime.SHORT,
) {
    class Builder private constructor() {

        private var messageResId: Int? = null
        private var messageString: String? = null
        private var type: MessageType = MessageType.INFO
        private var showTime: MessageShowTime = MessageShowTime.SHORT

        companion object {
            fun asSuccess(): Builder = Builder().apply { type = MessageType.SUCCESS }

            fun asInfo(): Builder = Builder().apply { type = MessageType.INFO }

            fun asError(): Builder = Builder().apply { type = MessageType.ERROR }
        }

        fun showLong(): Builder = apply { showTime = MessageShowTime.LONG }

        fun showShort(): Builder = apply { showTime = MessageShowTime.SHORT }

        fun setMessageResId(@StringRes resId: Int): Builder = apply { messageResId = resId }

        fun setMessageString(string: String): Builder = apply { messageString = string }

        fun build(): MessageBundle {
            return MessageBundle(
                messageRes = messageResId,
                messageText = messageString,
                type = type,
                showTime = showTime
            )
        }
    }
}