package com.aldajo92.tvmazeapp.data_sources.favorite_shows

import com.aldajo92.tvmazeapp.database.favorites_show.FavoriteShowDao
import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.mappers.asDatabaseModel
import com.aldajo92.tvmazeapp.mappers.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteShowsDataSourceImpl(
    private val favoriteShowDao: FavoriteShowDao
) : FavoriteShowsDataSource {
    override fun getFavoriteShow(showId: String): Show = Show()

    override fun saveFavoriteShow(show: Show) {
        favoriteShowDao.insertFavoriteShow(show.asDatabaseModel())
    }

    override fun getAllFavoriteShows(): Flow<List<Show>> =
        favoriteShowDao.getFavoritesShow().map {
            it?.asDomainModel() ?: emptyList()
        }

    override fun deleteFavoriteShow(showId: String) {
        favoriteShowDao.deleteById(showId)
    }
}
