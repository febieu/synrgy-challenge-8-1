package com.example.ch8.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.ch8.domain.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
