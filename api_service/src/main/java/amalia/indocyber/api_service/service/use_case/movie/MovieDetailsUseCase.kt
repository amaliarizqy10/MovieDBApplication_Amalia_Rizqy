package amalia.indocyber.api_service.service.use_case.movie

import amalia.indocyber.api_service.service.movie.MovieDetailsService
import amalia.indocyber.common.entity.base_response.ResponseApp
import amalia.indocyber.common.entity.movie.movie_detail.MovieDetailResponse
import kotlinx.coroutines.flow.flow

class MovieDetailsUseCase(val movieDetailsService: MovieDetailsService) {
    operator fun invoke(movieId:Int) = flow<ResponseApp<MovieDetailResponse>> {
        try {
            emit(ResponseApp.loading())
            val data = movieDetailsService.getMovieDetails(movieId)
            if (data.isSuccessful){
                data.body()?.let {
                    emit(ResponseApp.success(it))
                } ?: kotlin.run {
                    emit(ResponseApp.errorBackend(data.code(), data.errorBody()))
                }
            }else{
                emit(ResponseApp.errorBackend(data.code(), data.errorBody()))
            }
        }catch (e:Exception){
            emit(ResponseApp.errorSystem(e))
        }
    }
}