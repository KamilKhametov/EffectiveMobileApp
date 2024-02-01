package com.khametov.effectivemobileapp.common.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.util.Locale


fun Int?.orZero(): Int = this ?: 0
fun Long?.orZero(): Long = this ?: 0L
fun Float?.orZero(): Float = this ?: 0f
fun Double?.orZero(): Double = this ?: 0.0

fun Boolean?.orFalse(): Boolean = this ?: false
fun Boolean?.orTrue(): Boolean = this ?: true
fun Boolean?.toInt(): Int = if (this.orFalse()) 1 else 0

fun Int.getFormattedPrice(): String = String.format(
    Locale("ru"),
    "%,d",
    this
)
fun Number.toFormatRU(): String {
   val formattedText = String.format(
    Locale("ru"),
    when (this) {
        is Double, is Float -> "%,.02f"
        else -> "%,d"
    },
    this
)
    return "$formattedText ₽"
}

fun String.decodeBase64(): Bitmap {
    val bytes: ByteArray = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}