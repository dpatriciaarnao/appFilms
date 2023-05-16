package com.application.usecases.http

import androidx.lifecycle.MutableLiveData
import com.application.entities.Film
import com.application.entities.ObjectResult
import com.application.repository.remote.http.datasources.FilmDataSource
import com.application.repository.remote.models.exception.NetworkException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FilmUseCaseTest{

    @RelaxedMockK
    private lateinit var filmDataSource: FilmDataSource
    lateinit var filmUseCase: FilmUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        filmUseCase = FilmUseCase(filmDataSource)
    }

    @Test
    fun `when the api return something`() = runBlocking {
        //Given
        val myList = listOf(Film("Mario Bros", "MOVIE"))
        var resultFilms = MutableLiveData<List<Film>>()
        coEvery {
            val result = filmDataSource.getFilms()
            if (result is ObjectResult.Failure && result.exception is NetworkException) {
            } else if (result is ObjectResult.Success) {
                resultFilms.value = result.data
            }
            return@coEvery result
        }

        //When
        val result = filmUseCase

        //Then
        coVerify {
            val result = filmDataSource.getFilms()
            if (result is ObjectResult.Failure && result.exception is NetworkException) {
            } else if (result is ObjectResult.Success) {
               result.data
            }
        }
    }
}