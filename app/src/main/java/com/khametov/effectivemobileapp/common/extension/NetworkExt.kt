package com.khametov.effectivemobileapp.common.extension

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

inline fun <reified T> T.toRequestBody(): RequestBody {
    return Json.encodeToString(this)
        .toRequestBody("application/json".toMediaTypeOrNull())
}