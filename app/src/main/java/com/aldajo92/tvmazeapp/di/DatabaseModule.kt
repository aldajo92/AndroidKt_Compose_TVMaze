package com.aldajo92.tvmazeapp.di

import android.content.Context
import androidx.room.Room
import com.aldajo92.tvmazeapp.database.AppDatabase
import com.aldajo92.tvmazeapp.database.favorites_show.FavoriteShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Shows"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): FavoriteShowDao {
        return appDatabase.favoritesShowDao
    }

}
