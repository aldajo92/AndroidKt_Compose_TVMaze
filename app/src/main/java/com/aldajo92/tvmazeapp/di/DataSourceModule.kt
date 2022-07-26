package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.data_sources.episode.EpisodeDataSourceApiImpl
import com.aldajo92.tvmazeapp.data_sources.episode.EpisodeDataSource
import com.aldajo92.tvmazeapp.data_sources.show.ShowDataSource
import com.aldajo92.tvmazeapp.data_sources.show.ShowDataSourceApiImpl
import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.data_sources.favorite_shows.FavoriteShowsDataSourceImpl
import com.aldajo92.tvmazeapp.data_sources.favorite_shows.FavoriteShowsDataSource
import com.aldajo92.tvmazeapp.database.favorites_show.FavoriteShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DatabaseModule::class])
object DataSourceModule {

    @Provides
    @Singleton
    fun provideShowsAPIDataSource(
        api: TvMazeApi
    ): ShowDataSource =
        ShowDataSourceApiImpl(api)

    @Provides
    @Singleton
    fun provideEpisodeAPIDataSource(
        api: TvMazeApi
    ): EpisodeDataSource =
        EpisodeDataSourceApiImpl(api)

    @Provides
    @Singleton
    fun provideFavoritesShowsDataSource(
        favoriteShowDao: FavoriteShowDao
    ): FavoriteShowsDataSource =
        FavoriteShowsDataSourceImpl(favoriteShowDao)

}
