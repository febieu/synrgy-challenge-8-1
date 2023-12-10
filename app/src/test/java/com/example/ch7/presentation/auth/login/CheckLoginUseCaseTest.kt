package com.example.ch7.presentation.auth.login

import com.example.ch7.domain.repository.AuthRepository
import com.example.ch7.presentation.auth.login.usecase.CheckLoginUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CheckLoginUseCaseTest {
    private val authRepository = mock<AuthRepository>()

    private val useCase =
        CheckLoginUseCase(
            authRepository = authRepository,
        )

    @Test
    fun `test invoke return true when user login`() = runTest {
        whenever(authRepository.isLoggedIn()).thenReturn(flowOf(true))

        val actual = useCase.invoke().single()

        Assert.assertTrue(actual!!)
    }

    @Test
    fun`test invoke return false when user is not login` () = runTest {
        whenever(authRepository.isLoggedIn()).thenReturn(flowOf(false))

        val actual = useCase.invoke().single()

        Assert.assertFalse(actual!!)
    }
}