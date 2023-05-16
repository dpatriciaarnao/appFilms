package com.application.repository.remote.http.models.responses

import com.application.entities.Film


data class FilmsResponse(
    val results: List<FilmResponseItem>
)

data class FilmResponseItem(
    val name: String,
    val type: String,
){
    fun toFilmResponse(): Film = Film(
        this.name,
        this.type,

    )
}

fun List<FilmResponseItem>.toFilmsList(): List<Film> = this.map {
    it.toFilmResponse()
}


