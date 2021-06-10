package com.dicoding.expert.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.expert.core.domain.usecase.MovieUseCase

class FavouriteMovieViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val favoriteMovie = movieUseCase.getMovieFavourite().asLiveData()
}