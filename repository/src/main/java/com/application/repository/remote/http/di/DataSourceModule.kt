package com.application.repository.remote.http.di

import com.application.repository.remote.http.datasources.FilmDataSource
import com.application.repository.remote.http.interfaces.IFilmDataSource
import com.application.repository.remote.http.services.FilmService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideFilmDataSource(api: FilmService): IFilmDataSource = FilmDataSource(api)
}
