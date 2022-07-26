package com.aldajo92.tvmazeapp.database.favorites_show

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteShowDao {

    @Query("select * from favorites_shows")
    fun getFavoritesShow(): Flow<List<FavoriteShowEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritesShows(shows: List<FavoriteShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteShow(show: FavoriteShowEntity)

    @Query("DELETE FROM favorites_shows WHERE id = :key")
    fun deleteById(key: String)

    @Query("SELECT EXISTS (SELECT * FROM favorites_shows WHERE id = :key)")
    fun showFavoriteExists(key: String) : Boolean

}
