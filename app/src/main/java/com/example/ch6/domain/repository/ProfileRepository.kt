package com.example.ch6.domain.repository

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun loadUsername(): Flow<String?>
    suspend fun loadEmail(): Flow<String?>
    suspend fun logout()
}