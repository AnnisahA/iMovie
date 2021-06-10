package com.dicoding.expert.favourite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteMovieModule = module{
    viewModel { FavouriteMovieViewModel(get()) }
}