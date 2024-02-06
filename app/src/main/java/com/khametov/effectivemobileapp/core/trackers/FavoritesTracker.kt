package com.khametov.effectivemobileapp.core.trackers

import kotlinx.coroutines.flow.Flow

interface FavoritesTracker {

    fun observableFavorites(): Flow<Boolean>
    suspend fun setFavorites(state: Boolean)
}