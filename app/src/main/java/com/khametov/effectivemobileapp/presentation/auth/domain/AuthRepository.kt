package com.khametov.effectivemobileapp.presentation.auth.domain

interface AuthRepository {

    suspend fun saveUserData(name: String, surname: String, phone: String)
}