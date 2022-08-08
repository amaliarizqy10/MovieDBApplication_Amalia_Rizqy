package amalia.indocyber.api_service.service.movie

import amalia.indocyber.api_service.Constants
import amalia.indocyber.common.entity.movie.movie_review.MovieReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewsService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId:Int,
        @Query("page") page:Int,
        @Query("api_key") api_key:String = Constants.API_KEY_V3
    ):Response<MovieReviewsResponse>
}