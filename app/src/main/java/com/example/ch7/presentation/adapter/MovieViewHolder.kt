package com.example.ch7.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ch7.databinding.ItemMovieBinding
import com.example.ch7.domain.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.tvTitleMovie.text = movie.title
        binding.tvDescMovie.text = movie.restaurantChain
        binding.ivMovie.load(movie.image)
    }
}