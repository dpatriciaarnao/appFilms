package com.application.repository.remote.http.interfaces

import com.application.entities.Film
import com.application.entities.ObjectResult


interface IFilmDataSource {
    suspend fun getFilms(): ObjectResult<List<Film>>
}
