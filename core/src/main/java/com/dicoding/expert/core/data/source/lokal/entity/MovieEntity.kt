package com.dicoding.expert.core.data.source.lokal.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "original_language")
    var original_language: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "poster_path")
    var poster_path: String,

    @ColumnInfo(name = "release_date")
    var release_date: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable