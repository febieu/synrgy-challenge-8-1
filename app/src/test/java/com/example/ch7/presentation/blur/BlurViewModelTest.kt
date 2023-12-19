package com.example.ch7.presentation.blur

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.ch7.domain.repository.AccountRepository
import com.example.ch7.domain.repository.ImageRepository
import com.example.ch7.presentation.auth.login.LoginViewModel
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import io.mockk.every
import org.junit.Ignore

class BlurViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val imageRepository = mock<ImageRepository>()
    private val accountRepository = mock<AccountRepository>()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    private val viewModel =
        BlurViewModel(
            imageRepository = imageRepository,
            accountRepository = accountRepository,
        )

    @Test
    @Ignore
    fun `test applyBlur calls imageRepository applyBlur`() = runTest {
        viewModel.setImageUri("fakeUri")

        viewModel.applyBlur()

        coVerify{imageRepository.applyBlur(any())}
    }

    @Test
    @Ignore
    fun `loadProfilePhoto update _profilePhoto`() = runBlocking {
//        val profile = "fakeprofile"
//        every { accountRepository.loadProfilePhoto() } returns flow { emit(profile) }
//        val observer: Observer<String?> = mockk(relaxed = true)
//        viewModel.profilePhoto.observeForever(observer)
//
//        // When
//        blurViewModel.loadProfilePhoto()
//
//        // Then
//        verify { observer.onChanged(mockProfilePhoto) }

    }
}