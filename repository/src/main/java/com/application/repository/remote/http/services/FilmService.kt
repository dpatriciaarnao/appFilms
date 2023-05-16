package com.application.repository.remote.http.services

import com.application.repository.remote.http.models.responses.FilmsResponse
import com.application.repository.utils.DynamicProperties
import retrofit2.Response
import retrofit2.http.GET

interface FilmService {

    @GET(DynamicProperties.DEFAULT_BASE_URL+"72f66f33-9186-4b20-a9a6-2c65661bc9cf")
    suspend fun getFilms(): Response<FilmsResponse>
}
