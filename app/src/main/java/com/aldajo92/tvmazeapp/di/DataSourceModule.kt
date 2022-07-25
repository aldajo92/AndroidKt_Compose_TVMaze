package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.data_sources.episode.EpisodeAPIDataSourceImpl
import com.aldajo92.tvmazeapp.data_sources.episode.EpisodeDataSource
import com.aldajo92.tvmazeapp.data_sources.show.ShowDataSource
import com.aldajo92.tvmazeapp.data_sources.show.ShowsAPIDataSourceImpl
import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.data_sources.FavoriteShowsDataSourceImpl
import com.aldajo92.tvmazeapp.data_sources.FavoriteShowsDataSource
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
        ShowsAPIDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideEpisodeAPIDataSource(
        api: TvMazeApi
    ): EpisodeDataSource =
        EpisodeAPIDataSourceImpl(api)

    fun provideFavoritesShowsDataSource(
        favoriteShowDao: FavoriteShowDao
    ): FavoriteShowsDataSource =
        FavoriteShowsDataSourceImpl(favoriteShowDao)

}
