package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.data_sources.ShowDataSource
import com.aldajo92.tvmazeapp.data_sources.ShowsAPIDataSourceImpl
import com.aldajo92.tvmazeapp.network.TvMazeApi
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

}
