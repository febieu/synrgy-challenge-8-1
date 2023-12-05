package com.example.ch7.domain.usecase

interface AuthenticateUseCase {
    suspend fun invoke(username: String, password: String): String
}