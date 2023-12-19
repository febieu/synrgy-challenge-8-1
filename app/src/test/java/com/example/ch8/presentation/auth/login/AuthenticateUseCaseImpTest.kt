package com.example.ch8.presentation.auth.login

import com.example.ch8.domain.repository.AuthRepository
import com.example.ch8.presentation.auth.login.usecase.AuthenticateUseCaseImp
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AuthenticateUseCaseImpTest {
    private val authRepository = mock<AuthRepository>()

    private val useCase =
        AuthenticateUseCaseImp(
            authRepository = authRepository,
        )

    @Test
    @Ignore
    fun `test invoke return token`() =
        runTest {
            // Given
            val username = "febi"
            val password = "123456"
            val expected = "token"

            // When
            whenever(authRepository.authenticate(username, password)).thenReturn(expected)
            val actual = useCase.invoke(username, password)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test(expected = UnsupportedOperationException::class)
    fun `test invoke throws invalid username or password`() =
        runTest {
            // Given
            val username = ""
            val password = ""
            val errorMessage = "username dan password salah!"
            val expected = UnsupportedOperationException(errorMessage)

            // When
            whenever(authRepository.authenticate(username, password)).thenThrow(expected)
            useCase.invoke(username, password)
        }

    @Test(expected = UnsupportedOperationException::class)
    @Ignore
    fun `test invoke throws wrong username or password`() =
        runTest {
            // Given
            val username = "username"
            val password = "password"
            val errorMessage = "username atau password salah!"
            val expected = UnsupportedOperationException(errorMessage)

            // When
            whenever(authRepository.authenticate(username, password)).thenThrow(expected)
            useCase.invoke(username, password)
        }
}
