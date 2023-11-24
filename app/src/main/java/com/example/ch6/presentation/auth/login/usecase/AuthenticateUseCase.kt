package com.example.ch6.presentation.auth.login.usecase

import com.example.ch6.domain.repository.AuthRepository

class AuthenticateUseCase (
    private val authRepository: AuthRepository,
){
    suspend operator fun invoke(
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