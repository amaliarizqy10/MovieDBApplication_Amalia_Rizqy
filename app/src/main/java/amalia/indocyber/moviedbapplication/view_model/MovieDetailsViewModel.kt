package amalia.indocyber.moviedbapplication.view_model

import amalia.indocyber.api_service.service.use_case.movie.MovieDetailsUseCase
import amalia.indocyber.api_service.service.use_case.movie.MovieReviewsUseCase
import amalia.indocyber.api_service.service.use_case.movie.MovieVideoUseCase
import amalia.indocyber.common.base.BaseViewModel
import amalia.indocyber.common.entity.base_response.ResponseApp
import amalia.indocyber.common.entity.movie.movie_detail.MovieDetailResponse
import amalia.indocyber.common.entity.movie.movie_review.Review
import amalia.indocyber.common.ext.SingleLiveEvent
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    application: Application,
    val movieDetailsUseCase: MovieDetailsUseCase,
    val movieReviewsUseCase: MovieReviewsUseCase,
    val movieVideoUseCase: MovieVideoUseCase
) :BaseViewModel(application){

    val movieDetailsData = MutableLiveData<ResponseApp<MovieDetailResponse>>()
    val movieVideoData = SingleLiveEvent<String>()
    val movieReviewsData = MutableLiveData<PagingData<Review>>()

    fun getMovieDetails(movieId:Int){
        getMovieVideo(movieId)
        viewModelScope.launch {
            movieDetailsUseCase.invoke(movieId).collect{
                movieDetailsData.postValue(it)
            }
        }
        viewModelScope.launch {
            movieReviewsUseCase.invoke(movieId).collect{
                movieReviewsData.postValue(it)
            }
        }
    }

    fun getMovieVideo(movieId: Int){
        viewModelScope.launch {
            movieVideoUseCase.invoke(movieId).collect{
                it.data?.video?.get(0)?.key?.let {
                    it1 -> movieVideoData.postValue(it1)
                }
            }
        }
    }
}