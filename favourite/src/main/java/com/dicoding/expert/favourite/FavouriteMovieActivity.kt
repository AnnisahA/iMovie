package com.dicoding.expert.favourite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.expert.bismillah_movie_expert.detail.DetailMovieActivity
import com.dicoding.expert.core.ui.MovieAdapter
import com.dicoding.expert.favourite.databinding.ActivityFavouriteMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavouriteMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteMovieBinding
    private val favouriteMovieViewModel: FavouriteMovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favouriteMovieModule)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.MOVIE_DATA, selectedData)
            startActivity(intent)
        }

        favouriteMovieViewModel.favoriteMovie.observe(this, { movieList ->
            movieAdapter.movieData(movieList)
        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}