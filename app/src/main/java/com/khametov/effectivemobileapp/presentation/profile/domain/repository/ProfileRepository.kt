package com.khametov.effectivemobileapp.presentation.profile.domain.repository

interface ProfileRepository {

    suspend fun getUserFullName(): String
    suspend fun getUserPhone(): String
    suspend fun getFavoritesCount(): Int
    suspend fun clearUserData()
}