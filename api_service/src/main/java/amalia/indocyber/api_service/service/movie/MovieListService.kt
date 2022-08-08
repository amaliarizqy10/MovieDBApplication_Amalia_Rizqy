package amalia.indocyber.api_service.service.movie

import amalia.indocyber.api_service.Constants
import amalia.indocyber.common.entity.movie.movie_list.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {
    @GET("discover/movie")
    suspend fun getAllMovie(
        @Query("with_genres") genres:String,
        @Query("page") page:Int,
        @Query("api_key") api_key:String = Constants.API_KEY_V3
    ):Response<MovieListResponse>
}