package com.example.ch7.presentation.auth.login

import com.example.ch7.domain.repository.AuthRepository
import com.example.ch7.presentation.auth.login.usecase.SaveTokenUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class SaveTokenUseCaseTest {
    private val authRepository = mock<AuthRepository>()

    private val useCase =
        SaveTokenUseCase(
            authRepository = authRepository,
        )

    @Test
    fun `test saveToken calls saveToken#invoke`() = runTest {
            val token = "token"

            val actual = useCase.invoke(token)

            verify(authRepository).saveToken(token)
    }
}