package com.dicoding.expert.core.domain.repository

import com.dicoding.expert.core.data.Resource
import com.dicoding.expert.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies():Flow<Resource<List<Movie>>>

    fun getMovieFavourite(): Flow<List<Movie>>

    fun setMovieFavourite(movie: Movie, state: Boolean)
}