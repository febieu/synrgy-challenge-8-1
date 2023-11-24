package com.example.ch6.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ch6.databinding.ItemMovieBinding
import com.example.ch6.domain.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.tvTitleMovie.text = movie.originalTitle
        binding.tvDescMovie.text = movie.overview
        binding.ivMovie.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
    }
}