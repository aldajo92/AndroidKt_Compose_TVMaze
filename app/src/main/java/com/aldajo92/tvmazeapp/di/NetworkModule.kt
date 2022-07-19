package com.aldajo92.tvmazeapp.di

import com.aldajo92.tvmazeapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [TvMazeApiModule::class])
object NetworkModule {

    private const val BASE_URL = "https://api.tvmaze.com/"

    @Provides
    @Named(TVMAZE_BASE_URL_TAG)
    fun provideBaseUrlString() = BASE_URL

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


//    @Provides
//    @Singleton
//    fun provideCache(context: Context): Cache {
//        val cacheSize = 5 * 1024 * 1024 // 5 MB
//        val cacheDir = context.cacheDir
//        return Cache(cacheDir, cacheSize.toLong())
//    }
}
