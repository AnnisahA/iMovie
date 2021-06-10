package com.dicoding.expert.core.domain.usecase

import com.dicoding.expert.core.domain.model.Movie
import com.dicoding.expert.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovies() = movieRepository.getAllMovies()
    override fun getMovieFavourite() = movieRepository.getMovieFavourite()
    override fun setMovieFavourite(movie: Movie, state: Boolean) = movieRepository.setMovieFavourite(movie,state)
}