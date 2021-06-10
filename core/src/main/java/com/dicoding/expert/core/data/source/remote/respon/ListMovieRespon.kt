package com.dicoding.expert.core.data.source.remote.respon

import com.google.gson.annotations.SerializedName

data class ListMovieRespon(

    @SerializedName("page")
    val pages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<MovieRespon>
)