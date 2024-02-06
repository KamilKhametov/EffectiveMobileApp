package com.khametov.effectivemobileapp.core.trackers

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class FavoritesTrackerImpl @Inject constructor(): FavoritesTracker {

    private val favoritesStateFlow = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
        replay = 1
    )

    override suspend fun setFavorites(state: Boolean) {
        favoritesStateFlow.emit(value = state)
    }

    override fun observableFavorites(): Flow<Boolean> {
        return favoritesStateFlow.asSharedFlow()
    }
}