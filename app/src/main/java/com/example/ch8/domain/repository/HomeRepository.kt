package com.example.ch8.domain.repository

import com.example.ch8.domain.Movie

interface HomeRepository {
    suspend fun fetchMovies(): List<Movie>
}
