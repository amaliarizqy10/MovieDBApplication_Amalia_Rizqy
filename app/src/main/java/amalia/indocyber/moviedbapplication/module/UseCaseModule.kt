package amalia.indocyber.moviedbapplication.module

import amalia.indocyber.api_service.service.genre.GenreService
import amalia.indocyber.api_service.service.movie.MovieDetailsService
import amalia.indocyber.api_service.service.movie.MovieListService
import amalia.indocyber.api_service.service.movie.MovieReviewsService
import amalia.indocyber.api_service.service.movie.MovieVideoService
import amalia.indocyber.api_service.service.use_case.genre.GenreUseCase
import amalia.indocyber.api_service.service.use_case.movie.MovieDetailsUseCase
import amalia.indocyber.api_service.service.use_case.movie.MovieListUseCase
import amalia.indocyber.api_service.service.use_case.movie.MovieReviewsUseCase
import amalia.indocyber.api_service.service.use_case.movie.MovieVideoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGenreUseCase(genreService: GenreService) = GenreUseCase(genreService)

    @Provides
    fun provideMovieListUseCase(movieListService: MovieListService) =
        MovieListUseCase(movieListService)

    @Provides
    fun provideMovieDetailsUseCase(movieDetailsService: MovieDetailsService) =
        MovieDetailsUseCase(movieDetailsService)

    @Provides
    fun provideMovieReviewsUseCase(movieReviewsService: MovieReviewsService) =
        MovieReviewsUseCase(movieReviewsService)

    @Provides
    fun provideMovieVideoUseCase(movieVideoService: MovieVideoService) =
        MovieVideoUseCase(movieVideoService)
}