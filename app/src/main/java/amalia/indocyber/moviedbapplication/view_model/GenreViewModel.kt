package amalia.indocyber.moviedbapplication.view_model

import amalia.indocyber.api_service.service.use_case.genre.GenreUseCase
import amalia.indocyber.common.base.BaseViewModel
import amalia.indocyber.common.entity.base_response.ResponseApp
import amalia.indocyber.common.entity.genre.Genre
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.SelectionTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    application: Application,
    val genreUseCase: GenreUseCase
) : BaseViewModel(application){

    var selectionTracker : SelectionTracker<Long>? = null
    val genreData = MutableLiveData<ResponseApp<List<Genre>>>()

    init {
        loadAllGenre()
    }

    fun loadAllGenre(){
        viewModelScope.launch {
            genreUseCase.invoke().collect{
                genreData.postValue(it)
            }
        }
    }
}