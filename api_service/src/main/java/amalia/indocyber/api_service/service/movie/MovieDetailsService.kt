package amalia.indocyber.api_service.service.movie

import amalia.indocyber.api_service.Constants
import amalia.indocyber.common.entity.movie.movie_detail.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId:Int,
        @Query("api_key") api_key :String = Constants.API_KEY_V3
    ):Response<MovieDetailResponse>
}