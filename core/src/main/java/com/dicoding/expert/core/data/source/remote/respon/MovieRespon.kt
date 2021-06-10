package com.dicoding.expert.core.data.source.remote.respon

import com.google.gson.annotations.SerializedName

data class MovieRespon(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_language")
    val original_language: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("title")
    val title: String
)