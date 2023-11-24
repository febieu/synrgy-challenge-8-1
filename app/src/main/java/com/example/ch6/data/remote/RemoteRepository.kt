package com.example.ch6.data.remote

import com.example.ch6.data.remote.response.toMovie
import com.example.ch6.data.remote.service.TMDBService
import com.example.ch6.domain.Movie
import com.example.ch6.domain.repository.MovieRepository

class RemoteRepository(
    private val tmdbService: TMDBService,
    ) : MovieRepository {
    override suspend fun fetchMovies(): List<Movie> {
        return tmdbService.fetchMovies().results?.map { result -> result.toMovie() }.orEmpty()
    }
}