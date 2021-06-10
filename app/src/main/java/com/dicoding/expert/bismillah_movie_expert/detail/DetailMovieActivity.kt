package com.dicoding.expert.bismillah_movie_expert.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.expert.bismillah_movie_expert.R
import com.dicoding.expert.bismillah_movie_expert.databinding.ActivityDetailMovieBinding
import com.dicoding.expert.core.domain.model.Movie
import com.dicoding.expert.core.utils.IMovieNote
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_DATA = "movie_data"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieDetail = intent.getParcelableExtra<Movie>(MOVIE_DATA)
        if (movieDetail != null) {
            populateDetailMovie(movieDetail)
        }
    }

    private fun populateDetailMovie(data: Movie) {
        with(binding){
            titleMovie.text = data.title
            launchingMovie.text = data.release_date
            languageMovie.text = data.original_language
            description.text = data.overview
            popularMovie.text = data.popularity.toString()
            Glide.with(this@DetailMovieActivity)
                .load("${IMovieNote.POSTER}${data.poster_path}")
                .into(binding.posterMovie)

            var favourite = data.isFavorite
            statusFavorite(favourite)

            binding.ibFavMovie.setOnClickListener{
                favourite = !favourite
                detailMovieViewModel.favouriteMovie(data,favourite)
                statusFavorite(favourite)
            }
        }
    }

    private fun statusFavorite(statusFavorite: Boolean){
        if (statusFavorite) {
            binding.ibFavMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    this.applicationContext,
                    R.drawable.ic_fav
                )
            )
        } else {
            binding.ibFavMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    this.applicationContext,
                    R.drawable.ic_fav_border
                )
            )
        }
    }
}