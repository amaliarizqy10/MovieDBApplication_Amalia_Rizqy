package amalia.indocyber.moviedbapplication.module

import amalia.indocyber.api_service.retrofit.RetrofitClient
import amalia.indocyber.api_service.service.genre.GenreService
import amalia.indocyber.api_service.service.movie.MovieDetailsService
import amalia.indocyber.api_service.service.movie.MovieListService
import amalia.indocyber.api_service.service.movie.MovieReviewsService
import amalia.indocyber.api_service.service.movie.MovieVideoService
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit) = retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideMovieListService(retrofit: Retrofit) = retrofit.create(MovieListService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetaildService(retrofit: Retrofit) = retrofit.create(MovieDetailsService::class.java)

    @Provides
    @Singleton
    fun provideMovieReviewsService(retrofit: Retrofit) = retrofit.create(MovieReviewsService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideoService(retrofit: Retrofit) = retrofit.create(MovieVideoService::class.java)
}