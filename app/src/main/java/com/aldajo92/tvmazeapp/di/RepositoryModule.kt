package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.data_sources.episode.EpisodeDataSource
import com.aldajo92.tvmazeapp.data_sources.show.ShowDataSource
import com.aldajo92.tvmazeapp.repository.detail.ShowDetailRepository
import com.aldajo92.tvmazeapp.repository.detail.ShowDetailRepositoryImpl
import com.aldajo92.tvmazeapp.repository.search.SearchShowsRepository
import com.aldajo92.tvmazeapp.repository.search.SearchShowsRepositoryImpl
import com.aldajo92.tvmazeapp.repository.episodes.EpisodesRepository
import com.aldajo92.tvmazeapp.repository.episodes.EpisodesRepositoryImpl
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DataSourceModule::class])
object RepositoryModule {

    @Provides
    @Singleton
    fun provideShowRepository(
        showDataSource: ShowDataSource
    ): ShowRepository = ShowRepositoryImpl(showDataSource)

    @Provides
    @Singleton
    fun provideShowDetailRepository(): ShowDetailRepository = ShowDetailRepositoryImpl()

    @Provides
    @Singleton
    fun provideEpisodesRepository(
        showDataSource: EpisodeDataSource
    ): EpisodesRepository = EpisodesRepositoryImpl(showDataSource)

    @Provides
    @Singleton
    fun provideSearchShowsRepository(
        showDataSource: ShowDataSource
    ): SearchShowsRepository = SearchShowsRepositoryImpl(showDataSource)

}
