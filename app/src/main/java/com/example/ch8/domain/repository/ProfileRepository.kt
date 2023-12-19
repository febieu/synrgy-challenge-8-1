package com.example.ch8.domain.repository

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun loadUsername(): Flow<String?>

    suspend fun loadEmail(): Flow<String?>

    suspend fun logout()
}
