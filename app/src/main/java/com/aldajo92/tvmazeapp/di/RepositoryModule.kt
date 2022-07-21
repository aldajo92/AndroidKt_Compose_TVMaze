package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.repository.show_detail.ShowDetailRepository
import com.aldajo92.tvmazeapp.repository.show_detail.ShowDetailRepositoryImpl
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
    fun provideShowDetailRepository(
        api: TvMazeApi
    ): ShowDetailRepository = ShowDetailRepositoryImpl(api)

}
