package com.example.ch7.presentation.auth.register

import com.example.ch7.domain.repository.AuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RegisterUseCaseTest {
    private val authRepository = mock<AuthRepository>()

    private val useCase =
        RegisterUseCase(
            authRepository = authRepository,
        )
    @Test
    fun`test invoke calls register, input valid`() = runTest {
        val username = "test"
        val email = "test@example.com"
        val password = "password"
        val confirmPassword = "password"

        whenever(authRepository.validateInput(username, email, password, confirmPassword)).thenReturn(true)
        useCase.invoke(username, email, password, confirmPassword)

        verify(authRepository).register(username, email, password, confirmPassword)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun`test invoke throws exception, input not valid`() = runTest{
        val username = "abcdefg"
        val email = "12sawdada"
        val password = "22222"
        val confirmPassword = "222333"

        whenever(authRepository.validateInput(username, email, password, confirmPassword)).thenReturn(false)

        useCase.invoke(username, email, password, confirmPassword)

    }

}