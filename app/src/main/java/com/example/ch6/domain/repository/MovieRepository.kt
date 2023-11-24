package com.example.ch6.domain.repository

import com.example.ch6.domain.Movie

interface MovieRepository {
    suspend fun fetchMovies(): List<Movie>
}