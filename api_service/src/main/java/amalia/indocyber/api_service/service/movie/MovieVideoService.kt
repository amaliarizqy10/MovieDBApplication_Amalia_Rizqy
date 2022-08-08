package amalia.indocyber.api_service.service.movie

import amalia.indocyber.api_service.Constants
import amalia.indocyber.common.entity.movie.movie_video.MovieVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieVideoService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") movieId :Int,
        @Query("api_key") api_key :String = Constants.API_KEY_V3
    ):Response<MovieVideosResponse>
}