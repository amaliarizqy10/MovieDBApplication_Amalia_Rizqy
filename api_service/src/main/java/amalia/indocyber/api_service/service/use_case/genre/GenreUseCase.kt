package amalia.indocyber.api_service.service.use_case.genre

import amalia.indocyber.api_service.service.genre.GenreService
import amalia.indocyber.common.entity.base_response.ResponseApp
import amalia.indocyber.common.entity.genre.Genre
import kotlinx.coroutines.flow.flow

class GenreUseCase(val genreService: GenreService) {
    operator fun invoke() = flow<ResponseApp<List<Genre>>> {
        try {
            emit(ResponseApp.loading())
            val data = genreService.getAllGenre()
            if (data.isSuccessful){
                data.body()?.let {
                    emit(ResponseApp.success(it.genres))
                } ?: kotlin.run {
                    emit(ResponseApp.errorBackend(data.code(), data.errorBody()))
                }
            } else{
                emit(ResponseApp.errorBackend(data.code(), data.errorBody()))
            }
        }catch (e:Exception){
            emit(ResponseApp.errorSystem(e))
        }
    }
}