package amalia.indocyber.api_service.service.use_case.movie

import amalia.indocyber.api_service.service.movie.MovieVideoService
import amalia.indocyber.common.entity.base_response.ResponseApp
import amalia.indocyber.common.entity.movie.movie_video.MovieVideosResponse
import kotlinx.coroutines.flow.flow

class MovieVideoUseCase(val movieVideoService: MovieVideoService) {
    operator fun invoke(moveId:Int) = flow<ResponseApp<MovieVideosResponse>> {
        try {
            emit(ResponseApp.loading())
            val data = movieVideoService.getMovieVideo(moveId)
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