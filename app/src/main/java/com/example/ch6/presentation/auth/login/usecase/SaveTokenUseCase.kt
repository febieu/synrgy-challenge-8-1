package com.example.ch6.presentation.auth.login.usecase

import com.example.ch6.domain.repository.AuthRepository

class SaveTokenUseCase (
    private val authRepository: AuthRepository,
){
   suspend operator fun invoke(token: String) {
        authRepository.saveToken(token)
    }
}