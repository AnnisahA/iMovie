package com.dicoding.expert.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Movie(
    val movieId: Int,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val isFavorite: Boolean
) : Parcelable