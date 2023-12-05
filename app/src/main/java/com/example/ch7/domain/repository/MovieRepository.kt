package com.example.ch7.domain.repository

import com.example.ch7.domain.Movie

interface MovieRepository {
    suspend fun fetchMovies(): List<Movie>
}