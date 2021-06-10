package com.dicoding.expert.bismillah_movie_expert

import android.app.Application
import com.dicoding.expert.bismillah_movie_expert.di.useCaseModule
import com.dicoding.expert.bismillah_movie_expert.di.viewModuleModule
import com.dicoding.expert.core.di.databaseModule
import com.dicoding.expert.core.di.networkModule
import com.dicoding.expert.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class IMovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@IMovieApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModuleModule
                )
            )
        }
    }
}