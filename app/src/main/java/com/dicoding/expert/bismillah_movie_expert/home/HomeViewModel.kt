package com.dicoding.expert.bismillah_movie_expert.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.expert.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovies().asLiveData()
}