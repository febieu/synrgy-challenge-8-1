package com.example.ch6.domain.repository

import com.example.ch6.domain.Movie

interface HomeRepository {
    suspend fun fetchMovies(): List<Movie>
}