package amalia.indocyber.moviedbapplication.view_model

import amalia.indocyber.api_service.service.use_case.movie.MovieListUseCase
import amalia.indocyber.common.base.BaseViewModel
import amalia.indocyber.common.entity.movie.movie_list.Movie
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    application: Application,
    val movieListUseCase: MovieListUseCase
) :BaseViewModel(application){

    val movieData = MutableLiveData<PagingData<Movie>>()

    fun loadMovieList(genres:String){
        viewModelScope.launch {
            movieListUseCase.invoke(genres).collect{
                movieData.postValue(it)
            }
        }
    }
}