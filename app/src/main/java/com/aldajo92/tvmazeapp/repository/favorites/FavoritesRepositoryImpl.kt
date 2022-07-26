package com.aldajo92.tvmazeapp.repository.favorites

import com.aldajo92.tvmazeapp.data_sources.favorite_shows.FavoriteShowsDataSource
import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesRepositoryImpl(
    private val favoriteShowsDataSource: FavoriteShowsDataSource
) : FavoritesRepository {

    override fun saveFavoriteShow(show: Show) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteShowsDataSource.saveFavoriteShow(show)
        }
    }

    override fun getFlowData(): Flow<ShowsRequestStatus> =
        favoriteShowsDataSource.getAllFavoriteShows().map {
            ShowsRequestStatus.OnSuccess(it)
        }

}
