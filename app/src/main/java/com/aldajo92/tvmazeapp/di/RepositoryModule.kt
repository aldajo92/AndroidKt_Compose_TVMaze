package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.repository.show_episodes.EpisodesRepository
import com.aldajo92.tvmazeapp.repository.show_episodes.EpisodesRepositoryImpl
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepositoryImpl
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
    ): ShowRepository = ShowRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideEpisodesRepository(
        api: TvMazeApi
    ): EpisodesRepository = EpisodesRepositoryImpl(api)

}
