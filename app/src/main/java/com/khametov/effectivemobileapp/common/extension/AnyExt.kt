package com.khametov.effectivemobileapp.common.extension

import android.os.Handler
import android.os.Looper

fun Any?.isNull(): Boolean = this == null
fun Any?.isNotNull(): Boolean = this != null
fun postDelayed(delay: Long = 100, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(
        { block() },
        delay
    )
}