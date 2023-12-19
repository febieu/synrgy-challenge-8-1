package com.example.ch8.domain.repository

import com.example.ch8.domain.Movie

interface MovieRepository {
    suspend fun fetchMovies(): List<Movie>
}
