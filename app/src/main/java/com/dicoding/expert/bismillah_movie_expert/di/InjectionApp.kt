package com.dicoding.expert.bismillah_movie_expert.di

import com.dicoding.expert.bismillah_movie_expert.detail.DetailMovieViewModel
import com.dicoding.expert.bismillah_movie_expert.home.HomeViewModel
import com.dicoding.expert.core.domain.usecase.MovieInteractor
import com.dicoding.expert.core.domain.usecase.MovieUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModuleModule = module {
    viewModel { HomeViewModel(get())}
    viewModel { DetailMovieViewModel(get()) }
}