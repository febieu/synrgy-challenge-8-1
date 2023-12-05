package com.example.ch7.presentation.auth.login.usecase

import com.example.ch7.domain.repository.AuthRepository
import com.example.ch7.domain.usecase.AuthenticateUseCase

class AuthenticateUseCase (
    private val authRepository: AuthRepository,
) : AuthenticateUseCase{
    override suspend operator fun invoke(
        username: String, password: String
    ): String {
        if (authRepository.validateInput(username, password)){
            return authRepository.authenticate(username, password)
        }
        else{
            throw UnsupportedOperationException("username atau password tidak valid!")
        }
    }
}