package com.dicoding.expert.core.utils

import com.dicoding.expert.core.data.source.lokal.entity.MovieEntity
import com.dicoding.expert.core.data.source.remote.respon.MovieRespon
import com.dicoding.expert.core.domain.model.Movie
import java.util.ArrayList

object DataDummy{
    fun mapResponsesToEntities(input: List<MovieRespon>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                original_language = it.original_language,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                original_language = it.original_language,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                isFavorite = it.isFavorite
            )
        }


    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        original_language = input.original_language,
        overview = input.overview,
        popularity = input.popularity,
        poster_path = input.poster_path,
        release_date = input.release_date,
        title = input.title,
        isFavorite = input.isFavorite
    )
}