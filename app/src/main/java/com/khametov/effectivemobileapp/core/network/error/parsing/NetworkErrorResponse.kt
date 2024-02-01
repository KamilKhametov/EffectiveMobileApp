package com.khametov.effectivemobileapp.core.network.error.parsing

import kotlinx.serialization.SerialName

data class NetworkErrorResponse(
    @SerialName("error") val errorModel: NetworkErrorModel
)
