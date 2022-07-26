package com.aldajo92.tvmazeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aldajo92.tvmazeapp.database.favorites_show.FavoriteShowDao
import com.aldajo92.tvmazeapp.database.favorites_show.FavoriteShowEntity

@Database(entities = [FavoriteShowEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoritesShowDao: FavoriteShowDao
}
