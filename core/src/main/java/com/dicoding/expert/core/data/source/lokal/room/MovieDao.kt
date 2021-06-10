package com.dicoding.expert.core.data.source.lokal.room

import androidx.room.*
import com.dicoding.expert.core.data.source.lokal.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getMovieFavourite(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovieFavourite(movies: MovieEntity)
}