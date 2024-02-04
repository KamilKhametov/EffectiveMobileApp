package com.khametov.effectivemobileapp.core.network

import com.khametov.effectivemobileapp.presentation.catalog.data.model.CatalogDto
import retrofit2.http.GET

interface RestApi {

    @GET("https://run.mocky.io/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getCatalog(): CatalogDto
}