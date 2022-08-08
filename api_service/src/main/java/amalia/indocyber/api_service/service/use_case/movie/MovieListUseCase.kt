package amalia.indocyber.api_service.service.use_case.movie

import amalia.indocyber.api_service.Constants
import amalia.indocyber.api_service.paging.MovieListPager
import amalia.indocyber.api_service.service.movie.MovieListService


class MovieListUseCase (val movieListService: MovieListService) {
    operator fun invoke(genres : String) =
        MovieListPager.createPager(Constants.DEFAULT_PAGE_SIZE, movieListService, genres).flow
}