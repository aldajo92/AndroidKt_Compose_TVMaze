package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.repository.ShowRepository
import com.aldajo92.tvmazeapp.repository.ShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [TvMazeApiModule::class])
object RepositoryModule {

    @Provides
    @Singleton
    fun provideShowRepository(
        api: TvMazeApi
    ): ShowRepository =
        ShowRepositoryImpl(api)

}