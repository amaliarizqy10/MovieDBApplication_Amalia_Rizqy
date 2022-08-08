package amalia.indocyber.api_service.service.genre

import amalia.indocyber.api_service.Constants
import amalia.indocyber.common.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getAllGenre(
        @Query("api_key") api_key : String = Constants.API_KEY_V3
    ):Response<GenreResponse>
}