package com.dicoding.expert.core.data.source.remote.network

import com.dicoding.expert.core.data.source.remote.respon.ListMovieRespon
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/3/movie/now_playing")
    suspend fun getMovie(@Query("api_key") api_key: String): ListMovieRespon
}