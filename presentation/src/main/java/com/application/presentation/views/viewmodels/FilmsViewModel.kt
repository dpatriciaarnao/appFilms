package com.application.presentation.views.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.entities.Film
import com.application.entities.ObjectResult
import com.application.usecases.http.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val filmUseCase: FilmUseCase,
) : ViewModel() {

    private val _filmsData = MutableLiveData<List<Film>>()
    val filmsData: MutableLiveData<List<Film>> = _filmsData

    private val _viewState = MutableLiveData<FilmViewState>()
    val viewState: LiveData<FilmViewState> = _viewState

    fun loadDataMovies() {
        viewModelScope.launch {
            when (val result = filmUseCase.getFilms()) {
                is ObjectResult.Success -> {
                    _viewState.value = FilmViewState.LoadData.Success(
                        films = result.data
                    )
                    _filmsData.value = result.data!!
                }

                is ObjectResult.Failure -> {
                    _viewState.value = FilmViewState.LoadData.Failure(result.exception)
                }

                else -> {}
            }
        }
    }

}

sealed class FilmViewState {
    sealed class LoadData : FilmViewState() {
        object Processing : LoadData()
        data class Success(val films: List<Film>) : LoadData()
        data class Failure(val error: Throwable) : LoadData()
    }
}


