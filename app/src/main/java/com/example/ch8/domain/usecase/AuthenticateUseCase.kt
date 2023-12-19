package com.example.ch8.domain.usecase

interface AuthenticateUseCase {
    suspend fun invoke(
        username: String,
        password: String,
    ): String
}
