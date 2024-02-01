package com.khametov.effectivemobileapp.common.extension

fun <T> T?.ifNull(alternative: T): T = this ?: alternative