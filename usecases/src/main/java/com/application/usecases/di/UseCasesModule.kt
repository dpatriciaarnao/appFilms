package com.application.usecases.di

import android.content.Context
import com.application.repository.remote.http.interfaces.IFilmDataSource
import com.application.usecases.http.FilmUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Singleton
    @Provides
    fun provideMoviesUseCase(
        @ApplicationContext context: Context,
        filmDataSource: IFilmDataSource
    ): FilmUseCase = FilmUseCase(
        filmDataSource
    )
}
