package com.example.ch8.fake

import com.example.ch8.domain.usecase.AuthenticateUseCase

class FakeAuthenticateUseCase : AuthenticateUseCase {
    override suspend fun invoke(
        username: String,
        password: String,
    ): String {
        if (
            username.isNotEmpty() &&
            username.isNotBlank() &&
            password.isNotEmpty() &&
            password.isNotBlank()
        ) {
            return if (username == "febi" && password == "123456") {
                "token"
            } else {
                throw UnsupportedOperationException("username dan password salah!")
            }
        } else {
            throw UnsupportedOperationException("username atau password tidak valid!")
        }
    }
}
