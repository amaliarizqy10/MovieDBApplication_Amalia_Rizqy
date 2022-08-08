package amalia.indocyber.api_service.service.use_case.movie

import amalia.indocyber.api_service.Constants
import amalia.indocyber.api_service.paging.MovieReviewPager
import amalia.indocyber.api_service.service.movie.MovieReviewsService

class MovieReviewsUseCase(val movieReviewsService: MovieReviewsService) {
    operator fun invoke(movieId:Int) =
        MovieReviewPager.createPager(Constants.DEFAULT_PAGE_SIZE, movieReviewsService, movieId).flow
}