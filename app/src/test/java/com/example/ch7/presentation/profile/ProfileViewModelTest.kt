package com.example.ch7.presentation.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ch7.domain.repository.AccountRepository
import com.example.ch7.domain.repository.MovieRepository
import com.example.ch7.helper.MainDispatcherRule
import com.example.ch7.presentation.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ProfileViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private val accountRepository = mock<AccountRepository>()
    private val dispatcher = Dispatchers.Main


    private val viewModel =
        ProfileViewModel(
            accountRepository = accountRepository,
            dispatcher = dispatcher,
        )

    @Test
    fun`test loadProfile success`() = runTest {
        val username = "febi"
        val email = "febi@example.com"
        whenever(accountRepository.loadUsername()).thenReturn(flowOf(username))
        whenever(accountRepository.loadEmail()).thenReturn(flowOf(email))

        viewModel.loadProfile()

        advanceUntilIdle()
        verify(accountRepository).loadUsername()
        verify(accountRepository).loadEmail()
        assert(viewModel.loading.value == false)
        assert(viewModel.error.value == null)
        assert(viewModel.username.value == username)
        assert(viewModel.email.value == email)
    }
    @Test
    suspend fun`loadProfilePhoto success`(){
        val profile = "profile.jpg"
        whenever(accountRepository.loadProfilePhoto()).thenReturn(flowOf(profile))

        viewModel.loadProfilePhoto()

        verify(accountRepository).loadProfilePhoto()
        assert(viewModel.error.value == null)
        assert(viewModel.profilePhoto.value == profile)
    }
}