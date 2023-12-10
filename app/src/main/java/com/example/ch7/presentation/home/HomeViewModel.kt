package com.example.ch7.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ch7.domain.Movie
import com.example.ch7.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineContext,
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun fetchMovies() {
        _loading.value = true
        viewModelScope.launch(dispatcher) {
            runCatching {
                movieRepository.fetchMovies()
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { movies ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _movies.value = movies
                }
            }
        }
    }
}
