package com.application.repository.remote.models.exception

import com.google.gson.Gson
import okhttp3.ResponseBody
import java.io.IOException

class NetworkException(message: String) : IOException(message)

data class FilmError(override val message: String) : Exception(message)

fun ResponseBody?.toFilmError(): FilmError {
    return if (this == null) {
        FilmError("Error with empty response body")
    } else {
        val errorBodyStr = this.string()
        Gson().fromJson(errorBodyStr, FilmError::class.java)
    }
}
