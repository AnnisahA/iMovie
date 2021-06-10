package com.dicoding.expert.core.data.source.lokal

import com.dicoding.expert.core.data.source.lokal.entity.MovieEntity
import com.dicoding.expert.core.data.source.lokal.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getMovieFavourite()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMovieFavourite(movie)
    }
}