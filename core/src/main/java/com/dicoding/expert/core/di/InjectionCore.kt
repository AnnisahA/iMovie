package com.dicoding.expert.core.di

import androidx.room.Room
import com.dicoding.expert.core.data.MovieRepository
import com.dicoding.expert.core.data.source.lokal.LocalDataSource
import com.dicoding.expert.core.data.source.lokal.room.MovieDatabase
import com.dicoding.expert.core.data.source.remote.RemoteDataSource
import com.dicoding.expert.core.data.source.remote.network.APIService
import com.dicoding.expert.core.domain.repository.IMovieRepository
import com.dicoding.expert.core.utils.AppExecutor
import com.dicoding.expert.core.utils.IMovieNote
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, IMovieNote.DB
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0==")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120,TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(IMovieNote.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(APIService::class.java)
    }

}
val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<IMovieRepository>{ MovieRepository(get(),get(),get())}
}