package com.example.ch8.presentation.auth.login.usecase

import com.example.ch8.domain.repository.AuthRepository
import com.example.ch8.domain.usecase.AuthenticateUseCase

class AuthenticateUseCaseImp (
    private val authRepository: AuthRepository,
) : AuthenticateUseCase {
    override suspend operator fun invoke(
        username: String,
        password: String,
    ): String {
        return authRepository.authenticate(username, password)
    }
}
