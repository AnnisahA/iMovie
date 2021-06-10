package com.dicoding.expert.core.data

import com.dicoding.expert.core.data.source.lokal.LocalDataSource
import com.dicoding.expert.core.data.source.remote.RemoteDataSource
import com.dicoding.expert.core.data.source.remote.network.APIRespon
import com.dicoding.expert.core.data.source.remote.respon.MovieRespon
import com.dicoding.expert.core.domain.model.Movie
import com.dicoding.expert.core.domain.repository.IMovieRepository
import com.dicoding.expert.core.utils.AppExecutor
import com.dicoding.expert.core.utils.DataDummy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieRespon>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataDummy.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<APIRespon<List<MovieRespon>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieRespon>) {
                val movieData = DataDummy.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieData)
            }
        }.asFlow()

    override fun getMovieFavourite(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataDummy.mapEntitiesToDomain(it)
        }
    }

    override fun setMovieFavourite(movie: Movie, state: Boolean) {
        val movieData = DataDummy.mapDomainToEntity(movie)
        appExecutor.diskIO().execute{localDataSource.setFavoriteMovie(movieData,state)}
    }
}