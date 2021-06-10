package com.dicoding.expert.core.data.source.remote

import android.util.Log
import com.dicoding.expert.core.data.source.remote.network.APIRespon
import com.dicoding.expert.core.data.source.remote.network.APIService
import com.dicoding.expert.core.data.source.remote.respon.MovieRespon
import com.dicoding.expert.core.utils.IMovieNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: APIService) {

    suspend fun getAllMovies(): Flow<APIRespon<List<MovieRespon>>> {
        return flow {
            try {
                val response = apiService.getMovie(IMovieNote.KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(APIRespon.Success(response.results))
                } else {
                    emit(APIRespon.Empty)
                }
            } catch (e : Exception){
                emit(APIRespon.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}