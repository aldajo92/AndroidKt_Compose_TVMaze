package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.network.TvMazeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TvMazeApiModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideTvMazeApi(
        okHttpClient: OkHttpClient,
        @Named(TVMAZE_BASE_URL_TAG) baseUrl: String
    ): TvMazeApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(TvMazeApi::class.java)

}
