package com.aldajo92.tvmazeapp.database.favorites_show

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteShowDao {

    @Query("select * from FavoriteShowEntity")
    fun getFavoritesShow(): Flow<List<FavoriteShowEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritesShows(shows: List<FavoriteShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteShow(show: FavoriteShowEntity)

//    @Query("select * from EpisodeEntity WHERE show LIKE :show")
//    fun getEpisode(show: String): Flow<EpisodeEntity?>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertEpisode(episodeEntity: EpisodeEntity)
}
//
//@Database(entities = [ShowEntity::class, EpisodeEntity::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract val showDao: ShowDao
//}
