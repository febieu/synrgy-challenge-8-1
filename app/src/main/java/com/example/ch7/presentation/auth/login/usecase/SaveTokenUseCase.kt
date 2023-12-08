package com.example.ch7.presentation.auth.login.usecase

import com.example.ch7.domain.repository.AuthRepository

class SaveTokenUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(token: String) {
        authRepository.saveToken(token)
    }
}
