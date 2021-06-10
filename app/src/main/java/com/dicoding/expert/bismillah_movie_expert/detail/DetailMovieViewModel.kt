package com.dicoding.expert.bismillah_movie_expert.detail

import androidx.lifecycle.ViewModel
import com.dicoding.expert.core.domain.model.Movie
import com.dicoding.expert.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun favouriteMovie(movie: Movie,newStatus: Boolean) =
        movieUseCase.setMovieFavourite(movie,newStatus)
}