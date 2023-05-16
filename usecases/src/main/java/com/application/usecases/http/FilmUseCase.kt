package com.application.usecases.http

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.application.entities.Film
import com.application.entities.ObjectResult
import com.application.repository.remote.http.interfaces.IFilmDataSource
import com.application.repository.remote.models.exception.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext

class FilmUseCase(
    @ApplicationContext private val context: Context,
    private val filmDataSource: IFilmDataSource
) {

    private var _resultFilms = MutableLiveData<List<Film>>()
    var resultFilms: MutableLiveData<List<Film>> = _resultFilms

    suspend fun getFilms(): ObjectResult<List<Film>> {
        return try {
            val result = filmDataSource.getFilms()
            if (result is ObjectResult.Failure && result.exception is NetworkException) {
                return result
            } else if (result is ObjectResult.Success) {
                _resultFilms.value = result.data
            }
            return result
        } catch (e: java.lang.Exception) {
            ObjectResult.Failure(e)
        }
    }
}
