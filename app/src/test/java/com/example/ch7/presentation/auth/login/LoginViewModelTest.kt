package com.example.ch7.presentation.auth.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.ch7.domain.usecase.AuthenticateUseCase
import com.example.ch7.fake.FakeAuthenticateUseCase
import com.example.ch7.helper.MainDispatcherRule
import com.example.ch7.presentation.auth.login.usecase.CheckLoginUseCase
import com.example.ch7.presentation.auth.login.usecase.SaveTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.capture
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LoginViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private val authenticateUseCase: AuthenticateUseCase = FakeAuthenticateUseCase()
    private val saveTokenUseCase = mock<SaveTokenUseCase>()
    private val checkLoginUseCase = mock<CheckLoginUseCase>()
    private val dispatcher = Dispatchers.Main

    private val stringObserver = mock<Observer<String?>>()

    @Captor
    private lateinit var stringCaptor: ArgumentCaptor<String>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    private val viewModel =
        LoginViewModel(
            authenticateUseCase = authenticateUseCase,
            saveTokenUseCase = saveTokenUseCase,
            checkLoginUseCase = checkLoginUseCase,
            dispatcher = dispatcher,
        )

    @Ignore("terwakili test case yg lain karena sdh diganti dengan fake bkn mock lagi")
    @Test
    fun `test viewModel#authenticate calls authenticateUseCase#invoke`() =
        runTest {
            // Given
            val username = "username"
            val password = "password"

            // When
            viewModel.authenticate(username, password)

            // Then
            verify(authenticateUseCase).invoke(username, password)
        }

    @Test
    fun `test viewModel#authenticate returns token`() =
        runTest {
            // Given
            val username = "febi"
            val password = "123456"
            val expected = "token"
            val liveData = viewModel.authentication

            liveData.observeForever(stringObserver)

            // whenever(authenticateUseCase.invoke(username, password)).thenReturn(expected)

            // When
            viewModel.authenticate(username, password)
            verify(stringObserver).onChanged(capture(stringCaptor))

            // then
            val actual = stringCaptor.value
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test viewModel#authenticate throws invalid username or password`() =
        runTest {
            // Given
            val username = ""
            val password = ""
            val expected = "username atau password tidak valid!"
            // val throwable = UnsupportedOperationException(expected)
            val liveData = viewModel.error

            liveData.observeForever(stringObserver)
            // whenever(authenticateUseCase.invoke(username,password)).thenThrow(throwable)

            // When
            viewModel.authenticate(username, password)
            verify(stringObserver).onChanged(capture(stringCaptor))

            // Then
            val actual = stringCaptor.value
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test viewModel#authenticate throws `() =
        runTest {
            // Given
            val username = "username"
            val password = "password"
            val expected = "username dan password salah!"
            // val throwable = UnsupportedOperationException(expected)
            val liveData = viewModel.error
            liveData.observeForever(stringObserver)
            // whenever(authenticateUseCase.invoke(username, password)).thenThrow(throwable)

            // When
            viewModel.authenticate(username, password)
            verify(stringObserver).onChanged(capture(stringCaptor))

            // Then
            val actual = stringCaptor.value
            Assert.assertEquals(expected, actual)
        }
}
