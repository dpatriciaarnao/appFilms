package com.application.repository.remote.http.datasources

import com.application.entities.Film
import com.application.entities.ObjectResult
import com.application.repository.remote.http.interfaces.IFilmDataSource
import com.application.repository.remote.http.models.responses.toFilmsList
import com.application.repository.remote.http.services.FilmService
import com.application.repository.utils.retryIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmDataSource(private val filmService: FilmService) : IFilmDataSource {
    override suspend fun getFilms(): ObjectResult<List<Film>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retryIO {
                    this@FilmDataSource.filmService.getFilms()
                }
                if (!response.isSuccessful) {
                    ObjectResult.Failure(Exception(response.errorBody()?.toString()))
                } else {
                    ObjectResult.Success(response.body()?.results?.toFilmsList() ?: emptyList())
                }
            } catch (ex: Exception) {
                ObjectResult.Failure(ex)
            }
        }
    }
}
