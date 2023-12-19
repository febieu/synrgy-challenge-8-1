package com.example.ch8.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ch8.domain.Movie
import com.example.ch8.domain.repository.MovieRepository
import com.example.ch8.helper.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HomeViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private val movieRepository = mock<MovieRepository>()
    private val dispatcher = Dispatchers.Main

    private val viewModel =
        HomeViewModel(
            movieRepository = movieRepository,
            dispatcher = dispatcher,
        )

    @Test
    fun `fetchMovies success`() =
        runTest {
            val movies = listOf(
                Movie(1, "quaso", "1.png", "LA" ),
                Movie(2, "bread", "2.png", "Seattle" ),
                Movie(3, "spaghetti", "3.png", "italia" ),
                )
            whenever (movieRepository.fetchMovies()).thenReturn(movies)

            viewModel.fetchMovies()

            advanceUntilIdle()
            verify(movieRepository).fetchMovies()
            assert(viewModel.loading.value == false)
            assert(viewModel.error.value == null)
            assert(viewModel.movies.value == movies)
        }

    @Test
    fun `fetchMovies failure`() = runTest {
        val error = "error"
        whenever(movieRepository.fetchMovies()).thenAnswer{
            throw Exception(error)
        }

        viewModel.fetchMovies()

        advanceUntilIdle()
        verify(movieRepository).fetchMovies()
        assert(viewModel.loading.value == false)
        assert(viewModel.error.value == error)
        assert(viewModel.movies.value == null)
    }
}